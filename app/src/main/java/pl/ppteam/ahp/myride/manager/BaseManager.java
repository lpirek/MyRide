package pl.ppteam.ahp.myride.manager;

import java.util.ArrayList;
import java.util.List;

import pl.ppteam.ahp.myride.common.City;
import pl.ppteam.ahp.myride.query.CityQuery;
import pl.ppteam.ahp.myride.tool.Logger;

/**
 * Created by £ukasz on 2015-05-22.
 */
public class BaseManager {

    public static BaseManager instance;

    public static BaseManager getInstance() {
        if (instance == null) {
            instance = new BaseManager();
        }
        return instance;
    }

    public BaseManager() {
        Logger.info("Initialiaze BaseManager");
    }


    /*
     *
     */

    public List<City> getCityList(CityQuery query) {

        List<City> result = new ArrayList<City>();
        result.add(new City("Wroc³aw", 50, 40));
        result.add(new City("Kraków", 70, 30));
        result.add(new City("Warszawa", 70, 50));

        return result;
    }

}
