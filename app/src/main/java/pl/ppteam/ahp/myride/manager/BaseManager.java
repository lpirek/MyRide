package pl.ppteam.ahp.myride.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pl.ppteam.ahp.myride.common.City;
import pl.ppteam.ahp.myride.common.Criterium;
import pl.ppteam.ahp.myride.common.MeansOfTransport;
import pl.ppteam.ahp.myride.common.Ride;
import pl.ppteam.ahp.myride.query.CityQuery;
import pl.ppteam.ahp.myride.query.CriteriumQuery;
import pl.ppteam.ahp.myride.query.RideQuery;
import pl.ppteam.ahp.myride.tool.Logger;

/**
 * Created by Łukasz on 2015-05-22.
 * Rozwiązanie tymczasowe ze względu na krótki czas realizacji projektu
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
        add(new City(1, "Wrocław", 51, 17));
        add(new City(2, "Kraków", 50, 19));
        add(new City(3, "Warszawa", 52, 21));
        add(new City(4, "Toruń", 53, 18));
        add(new City(5, "Poznań", 52, 16));
        add(new City(6, "Łódz", 51, 19));
        add(new City(7, "Gdańsk", 54, 18));
        add(new City(8, "Gdynia", 54, 18));
        add(new City(9, "Sopot", 54, 18));
        add(new City(10, "Opole", 50, 17));
    }};

    ArrayList<Ride> rideDB = new ArrayList<Ride>() {{
        add(new Ride(1, MeansOfTransport.BUS, new City(1, "Wrocław", 51, 17), new City(2, "Kraków", 50, 19), 34.0, true,
                new Date(), null, 180));
        add(new Ride(2, MeansOfTransport.TRAIN, new City(1, "Wrocław", 51, 17), new City(2, "Kraków", 50, 19), 27.0, true,
                new Date(), null, 180));
        add(new Ride(3, MeansOfTransport.TRAIN, new City(1, "Wrocław", 51, 17), new City(2, "Kraków", 50, 19), 29.0, true,
                new Date(), null, 180));
        add(new Ride(4, MeansOfTransport.TRAIN, new City(1, "Wrocław", 51, 17), new City(2, "Kraków", 50, 19), 35.0, true,
                new Date(), null, 180));
        add(new Ride(5, MeansOfTransport.TRAIN, new City(1, "Wrocław", 51, 17), new City(2, "Kraków", 50, 19), 50.0, true,
                new Date(), null, 180));
        add(new Ride(6, MeansOfTransport.TRAIN, new City(1, "Wrocław", 51, 17), new City(2, "Kraków", 50, 19), 27.0, true,
                new Date(), null, 180));
        add(new Ride(7, MeansOfTransport.TRAIN, new City(1, "Wrocław", 51, 17), new City(2, "Kraków", 50, 19), 35.0, true,
                new Date(), null, 180));
        add(new Ride(8, MeansOfTransport.TRAIN, new City(1, "Wrocław", 51, 17), new City(2, "Kraków", 50, 19), 37.0, true,
                new Date(), null, 180));
    }};

    ArrayList<Criterium> criteriumDB = new ArrayList<Criterium>() {{
        add(new Criterium(1, "Cena"));
        add(new Criterium(2, "Długość podróży"));
        add(new Criterium(3, "Komfort"));
        add(new Criterium(4, "Godzina odjazdu"));
        add(new Criterium(5, "Godzina przyjazdu"));
        add(new Criterium(6, "Rozmiar bagażu"));
        add(new Criterium(7, "Dostęp do toalety"));
        add(new Criterium(8, "Odległość od punktu docelowego"));
    }};

    public List<City> getCityList(CityQuery query) {

        List<City> result = new ArrayList<City>();

        for(City city : cityDB) {

            boolean pass = true;

            if (query.getName() != null) {
                pass = pass && query.getName().toLowerCase().equals(city.getName().toLowerCase());
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
                pass = pass && query.getName().toLowerCase().equals(city.getName().toLowerCase());
            }

            if (pass) {
                return city;
            }
        }

        return null;
    }

    public List<Ride> getRideList(RideQuery query) {

        List<Ride> result = new ArrayList<Ride>();

        for(Ride ride : rideDB) {

            boolean pass = true;

            if (query.getFromCity() != null) {
                pass = pass && query.getFromCity().getId() == ride.getFromCity().getId();
            }

            if (query.getToCity() != null) {
                pass = pass && query.getToCity().getId() == ride.getToCity().getId();
            }

            if (pass) {
                result.add(ride);
            }
        }

        return result;
    }

    public List<Criterium> getCriteriumList(CriteriumQuery query) {

        List<Criterium> result = new ArrayList<Criterium>();

        for(Criterium criterium : criteriumDB) {

            boolean pass = true;

            if (query.getName() != null) {
                pass = pass && query.getName().toLowerCase().equals(criterium.getName().toLowerCase());
            }

            if (pass) {
                result.add(criterium);
            }
        }

        return result;
    }

}
