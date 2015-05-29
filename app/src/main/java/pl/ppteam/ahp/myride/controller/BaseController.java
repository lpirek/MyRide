package pl.ppteam.ahp.myride.controller;

import android.service.notification.NotificationListenerService;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import pl.ppteam.ahp.myride.common.City;
import pl.ppteam.ahp.myride.common.CriteriaCompare;
import pl.ppteam.ahp.myride.common.Matrix;
import pl.ppteam.ahp.myride.common.MeansOfTransport;
import pl.ppteam.ahp.myride.common.Ride;
import pl.ppteam.ahp.myride.common.RideCompare;
import pl.ppteam.ahp.myride.query.RideQuery;
import pl.ppteam.ahp.myride.common.Criterium;
import pl.ppteam.ahp.myride.query.CriteriumQuery;
import pl.ppteam.ahp.myride.tool.Logger;

/**
 * Created by Łukasz on 2015-05-26.
 */
public class BaseController {

    public static BaseController instance;

    public static BaseController getInstance() {
        if (instance == null) {
            instance = new BaseController();
        }
        return instance;
    }

    public BaseController() {
        Logger.info("Initialiaze BaseManager");
    }

    /*
     *
     */

    private RideQuery rideQuery;
    private CriteriumQuery criteriumQuery;

    private List<Ride> selectedRides;
    private Map<String, List<RideCompare>> selectedRidesCompare;

    private List<Criterium> selectedCriteria;
    private List<CriteriaCompare> selectedCriteriaCompare;

    private Matrix criteriaMatrix;
    private Map<String, Matrix> ridesMatrix;

    private List<Ride> rankingRides;

    private int index;


    public Criterium getCurrentCriterium() {
        return selectedCriteria.get(index);
    }

    public boolean hasNextCriterium() {
        return index + 1 < selectedCriteria.size();
    }

    public void nextCriterium() {
        index++;

        if (index > selectedCriteria.size()) {
            index = selectedCriteria.size();
        }
    }

    public void prevCriterium() {
        index--;

        if (index < 0) {
            index = 0;
        }
    }

    //Ride

    public RideQuery getRideQuery() {
        return rideQuery;
    }

    public void setRideQuery(RideQuery rideQuery) {
        this.rideQuery = rideQuery;
    }

    public List<Ride> getSelectedRides() {
        return selectedRides;
    }

    public void setSelectedRides(List<Ride> selectedRides) {
        this.selectedRides = selectedRides;
        this.selectedRidesCompare = null;
        this.ridesMatrix = null;
        this.index = 0;
    }

    private Map<String, List<RideCompare>> initSelectedRidesCompare() {

        if (selectedRidesCompare == null) {
            selectedRidesCompare = new HashMap<String, List<RideCompare>>();

            for (Criterium criterium : selectedCriteria) {

                List<RideCompare> ridesCompare = new ArrayList<RideCompare>();

                for (int i = 0; i < selectedRides.size() - 1; i++) {
                    for (int k = i + 1; k < selectedRides.size(); k++) {
                        RideCompare compare = new RideCompare(selectedRides.get(i), selectedRides.get(k));
                        ridesCompare.add(compare);
                    }
                }

                selectedRidesCompare.put(criterium.getName(), ridesCompare);
            }

        }

        return selectedRidesCompare;
    }

    public List<RideCompare> getSelectedRidesCompare(Criterium criterium) {
        if (selectedRidesCompare == null) {
            initSelectedRidesCompare();
        }

        if (selectedRidesCompare.containsKey(criterium.getName())) {
            return selectedRidesCompare.get(criterium.getName());
        }
        else {
            return null;
        }
    }

    public boolean confirmRidesCompare(Criterium criterium) {

        if (ridesMatrix == null) {
            ridesMatrix = new HashMap<String, Matrix>();
        }

        List<RideCompare> compare = getSelectedRidesCompare(criterium);

        Matrix rideMatrix = new Matrix();
        rideMatrix.createRidesMatrix(compare, selectedRides.size());

        ridesMatrix.put(criterium.getName(), rideMatrix);

        return rideMatrix.isConsistent();
    }

    //Criterium

    public CriteriumQuery getCriteriumQuery()
    {
        return  criteriumQuery;
    }

    public void setCriteriumQuery(CriteriumQuery criteriumQuery) {
        this.criteriumQuery = criteriumQuery;
    }

    public List<Criterium> getSelectedCriteria()
    {
        return selectedCriteria;
    }

    public void setSelectedCriteria(List<Criterium> selectedCriteria) {
        this.selectedCriteria = selectedCriteria;
        this.selectedCriteriaCompare = null;
        this.criteriaMatrix = null;
        this.index = 0;
    }

    public List<CriteriaCompare> getSelectedCriteriaCompare() {

        if (selectedCriteriaCompare == null) {
            selectedCriteriaCompare = new ArrayList<>();

            for (int i = 0; i < selectedCriteria.size() - 1; i++) {
                for (int k = i + 1; k < selectedCriteria.size(); k++) {
                    CriteriaCompare compare = new CriteriaCompare(selectedCriteria.get(i), selectedCriteria.get(k));
                    selectedCriteriaCompare.add(compare);
                }
            }

        }

        return selectedCriteriaCompare;
    }

    public boolean confirmCriteriaCompare() {

        criteriaMatrix = new Matrix();
        criteriaMatrix.createCriteriaMatrix(selectedCriteriaCompare, selectedCriteria.size());

        return criteriaMatrix.isConsistent();
    }

    //Ranking

    public void calculateRankingRide() {

        rankingRides = new ArrayList<Ride>(selectedRides);

        double[] criteriaR = criteriaMatrix.getR();

        for (int i = 0; i < selectedRides.size(); i++) {
            double value = 0;

            for (int k = 0; k < selectedCriteria.size(); i++) {
                value += criteriaR[k] * ridesMatrix.get(selectedCriteria.get(k).getName()).getR()[i];
            }

            rankingRides.get(i).setRankingValue(value);
        }

        //Sortowanie według rankingValue
        Collections.sort(rankingRides, new Comparator<Ride>() {
            @Override
            public int compare(Ride lhs, Ride rhs) {
                return lhs.getRankingValue() > rhs.getRankingValue() ? 1 :
                        lhs.getRankingValue() < rhs.getRankingValue() ? - 1 : 0;
            }
        });

        setRankingPositions();
    }

    private void setRankingPositions() {

        for (int i = 0; i < rankingRides.size(); i++) {
            int position = i + 1;

            if (i > 0 && rankingRides.get(i).getRankingValue() ==
                    rankingRides.get(i - 1).getRankingValue()) {
                position = rankingRides.get(i - 1).getRankingPosition();
            }

            rankingRides.get(i).setRankingPosition(position);
        }

    }

    public List<Ride> getRankingRide()
    {
        return rankingRides;
    }


}