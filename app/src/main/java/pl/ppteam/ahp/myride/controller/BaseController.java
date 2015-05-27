package pl.ppteam.ahp.myride.controller;

import java.util.List;

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
    private List<Ride> selectedRides;
    private CriteriumQuery criteriumQuery;
    private List<Criterium> selectedCriterium;

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

    public void setCriteriumQuery(CriteriumQuery criteriumQuery)
    {
        this.criteriumQuery = criteriumQuery;
    }

    public List<Criterium> getSelectedCriteriums()
    {
        return selectedCriterium;
    }

    public void setSelectedCriteriums(List<Criterium> selectedCriterium)
    {
        this.selectedCriterium = selectedCriterium;
    }
}
