package pl.ppteam.ahp.myride.manager;

import java.util.ArrayList;
import java.util.List;

import pl.ppteam.ahp.myride.common.City;
import pl.ppteam.ahp.myride.common.db.RideQueryDb;
import pl.ppteam.ahp.myride.query.CityQuery;
import pl.ppteam.ahp.myride.query.RideQuery;

/**
 * Created by Łukasz on 2015-05-22.
 */
public class MainScreenManager {

    private List<RideQuery> lastRideQueries;

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

    public List<RideQuery> getLastRideQueries() {

        List<RideQuery> result = new ArrayList<RideQuery>();
        List<RideQueryDb> rideQueryDbs = RideQueryDb.getFirstRideQueries(5);

        for (RideQueryDb queryDb : rideQueryDbs) {
            RideQuery query = new RideQuery();

            CityQuery fromCityQuery = new CityQuery();
            fromCityQuery.setSymbol(queryDb.getCityFrom());

            query.setFromCity(this.getCity(fromCityQuery));

            CityQuery toCityQuery = new CityQuery();
            toCityQuery.setSymbol(queryDb.getCityTo());

            query.setToCity(this.getCity(toCityQuery));

            query.setStartDate(queryDb.getStartDate());

            result.add(query);
        }

        return result;
    }
}
