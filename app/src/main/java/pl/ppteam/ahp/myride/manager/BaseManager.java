package pl.ppteam.ahp.myride.manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    City city1 = new City(1, "Wrocław", "WRO", 51, 17);
    City city2 = new City(2, "Kraków", "KRK", 50, 19);
    City city3 = new City(3, "Warszawa", "WAW", 52, 21);
    City city4 = new City(4, "Toruń", "TOR", 53, 18);
    City city5 = new City(5, "Poznań", "POZ", 52, 16);
    City city6 = new City(6, "Łódz", "ŁDŹ", 51, 19);

    City city7 = new City(7, "Gdańsk", "GDA", 54, 18);
    City city8 = new City(8, "Gdynia", "GDY", 54, 18);
    City city9 = new City(9, "Sopot", "SOP", 54, 18);
    City city10 = new City(10, "Opole", "OPL", 50, 17);

    ArrayList<City> cityDB = new ArrayList<City>() {{
        add(city1);
        add(city2);
        add(city3);
        add(city4);
        add(city5);
        add(city6);
    }};

    ArrayList<Ride> rideDB = new ArrayList<Ride>() {{
        try {
            add(new Ride(1, MeansOfTransport.BUS, city1, city2, 34.0, true,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-10 12:25"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-10 15:25"), 180));

            add(new Ride(2, MeansOfTransport.TRAIN, city1, city2, 27.0, true,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-09 23:10"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-10 3:20"), 250));

            add(new Ride(3, MeansOfTransport.PLANE, city1, city2, 60.0, true,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-09 19:10"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-09 20:00"), 50));

            add(new Ride(4, MeansOfTransport.CAR, city1, city2, 35.0, true,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-09 18:30"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-09 21:00"), 150));

            add(new Ride(5, MeansOfTransport.CAR, city1, city2, 42.0, true,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-10 7:30"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-10 11:00"), 210));

            add(new Ride(7, MeansOfTransport.BUS, city1, city2, 35.0, true,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-09 15:30"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-09 19:00"), 210));

            add(new Ride(8, MeansOfTransport.TRAIN, city1, city2, 37.0, true,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-10 22:00"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-11 1:30"), 210));

            add(new Ride(9, MeansOfTransport.BUS, city1, city5, 34.0, true,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-10 12:25"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-10 15:25"), 180));

            add(new Ride(10, MeansOfTransport.TRAIN, city5, city3, 27.0, true,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-09 23:10"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-10 3:20"), 250));

            add(new Ride(11, MeansOfTransport.PLANE, city3, city5, 60.0, true,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-09 19:10"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-09 20:00"), 50));

            add(new Ride(12, MeansOfTransport.CAR, city6, city4, 35.0, true,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-09 18:30"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-09 21:00"), 150));

            add(new Ride(13, MeansOfTransport.CAR, city1, city4, 42.0, true,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-10 7:30"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-10 11:00"), 210));

            add(new Ride(14, MeansOfTransport.BUS, city6, city4, 35.0, true,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-09 15:30"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-09 19:00"), 210));

            add(new Ride(15, MeansOfTransport.TRAIN, city4, city3, 37.0, true,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-10 22:00"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-11 1:30"), 210));

            add(new Ride(16, MeansOfTransport.BUS, city2, city3, 34.0, true,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-10 12:25"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-10 15:25"), 180));

            add(new Ride(17, MeansOfTransport.TRAIN, city4, city5, 27.0, true,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-09 23:10"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-09 3:20"), 250));

            add(new Ride(18, MeansOfTransport.PLANE, city5, city6, 60.0, true,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-09 19:10"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-09 20:00"), 50));

            add(new Ride(19, MeansOfTransport.CAR, city2, city5, 35.0, true,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-09 18:30"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-09 21:00"), 150));

            add(new Ride(20, MeansOfTransport.CAR, city5, city2, 42.0, true,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-10 7:30"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-10 11:00"), 210));

            add(new Ride(21, MeansOfTransport.BUS, city5, city2, 35.0, true,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-09 15:30"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-09 19:00"), 210));

            add(new Ride(22, MeansOfTransport.TRAIN, city5, city4, 37.0, true,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-10 22:00"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-11 1:30"), 210));

            add(new Ride(23, MeansOfTransport.BUS, city3, city5, 34.0, true,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-09 12:25"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-09 15:25"), 180));

            add(new Ride(24, MeansOfTransport.TRAIN, city5, city3, 27.0, true,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-09 23:10"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-10 3:20"), 250));

            add(new Ride(25, MeansOfTransport.PLANE, city3, city5, 60.0, true,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-09 19:10"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-09 20:00"), 50));

            add(new Ride(26, MeansOfTransport.CAR, city6, city4, 35.0, true,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-09 18:30"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-09 21:00"), 150));

            add(new Ride(27, MeansOfTransport.CAR, city2, city4, 42.0, true,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-10 7:30"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-10 11:00"), 210));

            add(new Ride(28, MeansOfTransport.BUS, city6, city4, 32.0, true,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-10 15:30"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-10 19:00"), 210));

            add(new Ride(29, MeansOfTransport.TRAIN, city6, city3, 37.0, true,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-10 22:00"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-06-11 1:30"), 210));

        } catch (ParseException e1) {
            e1.printStackTrace();
        }
    }};


    ArrayList<Criterium> criteriumDB = new ArrayList<Criterium>() {{
        add(new Criterium(1, "Cena", "PRICE"));
        add(new Criterium(2, "Długość podróży", "TIME"));
        add(new Criterium(3, "Komfort", "COMFORT"));
        add(new Criterium(4, "Godzina odjazdu", "START"));
        add(new Criterium(5, "Godzina przyjazdu", "ARRIVE"));
        add(new Criterium(6, "Rozmiar bagażu", "LUGGAGE"));
        add(new Criterium(7, "Dostęp do toalety", "TOILET"));
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

            if (query.getId() != 0) {
                pass = pass && query.getId() == city.getId();
            }

            if (query.getName() != null) {
                pass = pass && query.getName().toLowerCase().equals(city.getName().toLowerCase());
            }

            if (query.getSymbol() != null) {
                pass = pass && query.getSymbol().equals(city.getSymbol());
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

            if (query.getStartDate() != null) {
                pass = pass && (ride.getStartDate().equals(query.getStartDate()) || ride.getStartDate().after(query.getStartDate()));
            }

            if (pass) {
                result.add(ride);
            }
        }

        Collections.sort(result, new Comparator<Ride>() {
            @Override
            public int compare(Ride lhs, Ride rhs) {
                return lhs.getStartDate().compareTo(rhs.getStartDate());
            }
        });

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
