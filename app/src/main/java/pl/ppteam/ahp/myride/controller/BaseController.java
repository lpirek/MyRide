package pl.ppteam.ahp.myride.controller;

import android.service.notification.NotificationListenerService;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import pl.ppteam.ahp.myride.common.City;
import pl.ppteam.ahp.myride.common.CriteriaCompare;
import pl.ppteam.ahp.myride.common.MeansOfTransport;
import pl.ppteam.ahp.myride.common.Ride;
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


    private RideQuery rideQuery;
    private CriteriumQuery criteriumQuery;

    private List<Ride> selectedRides;
    private List<Criterium> selectedCriteria;
    private List<CriteriaCompare> selectedCriteriaCompare;


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
    }

    public List<CriteriaCompare> getSelectedCriteriaCompare() {

        selectedCriteriaCompare = new ArrayList<>();

        for (int i = 0; i < selectedCriteria.size() - 1; i++) {
            for (int k = i + 1; k <selectedCriteria.size(); k++) {

                CriteriaCompare compare = new CriteriaCompare(selectedCriteria.get(i), selectedCriteria.get(k));
                selectedCriteriaCompare.add(compare);

            }
        }

        return selectedCriteriaCompare;
    }

    //Ranking

    public List<Ride> getRankingRide()
    {
        ArrayList<Ride> rankingRide = new ArrayList<Ride>() {{
            add(0,new Ride(1, MeansOfTransport.BUS, new City(1, "Wrocław", 51, 17), new City(2, "Kraków", 50, 19), 34.0, true,
                    new Date(), null, 180));
            add(1,new Ride(2, MeansOfTransport.TRAIN, new City(1, "Wrocław", 51, 17), new City(2, "Kraków", 50, 19), 27.0, true,
                    new Date(), null, 180));
            add(2,new Ride(3, MeansOfTransport.CAR, new City(1, "Wrocław", 51, 17), new City(2, "Kraków", 50, 19), 29.0, true,
                    new Date(), null, 180));
        }};

        return rankingRide;
    }


}