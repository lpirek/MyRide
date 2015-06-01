package pl.ppteam.ahp.myride.manager;

import java.util.ArrayList;
import java.util.List;

import pl.ppteam.ahp.myride.common.City;
import pl.ppteam.ahp.myride.query.CityQuery;

/**
 * Created by Łukasz on 2015-05-22.
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

    public List<String> getCityNames() {

        List<City> cityList = this.getCityList();
        List<String> result = new ArrayList<String>();

        for (City city : cityList) {
            result.add(city.getName());
        }

        return result;
    }
}
