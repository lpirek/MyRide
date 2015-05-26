package pl.ppteam.ahp.myride.manager;

import java.util.List;

import pl.ppteam.ahp.myride.common.City;
import pl.ppteam.ahp.myride.query.CityQuery;

/**
 * Created by £ukasz on 2015-05-22.
 */
public class MainScreenManager {

    public List<City> getCityList() {
        List<City> result = null;

        BaseManager manager = BaseManager.getInstance();
        result = manager.getCityList(new CityQuery());

        return result;
    }

    public City getCity(CityQuery cityQuery) {
        City result = null;

        BaseManager manager = BaseManager.getInstance();
        result = manager.getCity(cityQuery);

        return result;
    }

}
