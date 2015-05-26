package pl.ppteam.ahp.myride.controller;

import pl.ppteam.ahp.myride.query.RideQuery;
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

    public RideQuery getRideQuery() {
        return rideQuery;
    }

    public void setRideQuery(RideQuery rideQuery) {
        this.rideQuery = rideQuery;
    }


}
