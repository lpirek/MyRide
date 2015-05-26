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

    ArrayList<City> cityDB = new ArrayList<City>() {{
        add(new City(1, "Wroclaw", 51, 17));
        add(new City(2, "Krakow", 50, 19));
        add(new City(3, "Warszawa", 52, 21));
        add(new City(4, "Torun", 53, 18));
        add(new City(5, "Poznañ", 52, 16));
        add(new City(6, "Lodz", 51, 19));
        add(new City(7, "Gdansk", 54, 18));
        add(new City(8, "Gdynia", 54, 18));
        add(new City(9, "Sopot", 54, 18));
        add(new City(10, "Opole", 50, 17));
    }};

    public List<City> getCityList(CityQuery query) {

        List<City> result = new ArrayList<City>();

        for(City city : cityDB) {

            boolean pass = true;

            if (query.getName() != null) {
                pass = pass && query.getName().equals(city.getName());
            }

            if (pass) {
                result.add(city);
            }
        }

        return result;
    }

    public City getCity(CityQuery query) {

        for(City city : cityDB) {

            boolean pass = true;

            if (query.getName() != null) {
                pass = pass && query.getName().equals(city.getName());
            }

            if (pass) {
                return city;
            }
        }

        return null;
    }

}
