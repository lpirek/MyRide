package pl.ppteam.ahp.myride.common;

import java.util.Date;

/**
 * Created by £ukasz on 2015-05-22.
 */
public class Ride extends Item{

    private MeansOfTransport transportType;

    private String url;         //ewentualny odnoœnik do strony ze Ÿród³em

    private City fromCity;
    private City toCity;

    private double price;
    private boolean toilet;     //czy pojazd wyposa¿ony jest w toaletê

    private Date startDate;
    private Date endDate;
    private int rideTime;       //d³ugoœæ wycieczki w minutach


    public City getFromCity() {
        return fromCity;
    }

    public void setFromCity(City fromCity) {
        this.fromCity = fromCity;
    }

    public MeansOfTransport getTransportType() {
        return transportType;
    }

    public void setTransportType(MeansOfTransport transportType) {
        this.transportType = transportType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public City getToCity() {
        return toCity;
    }

    public void setToCity(City toCity) {
        this.toCity = toCity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isToilet() {
        return toilet;
    }

    public void setToilet(boolean toilet) {
        this.toilet = toilet;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getRideTime() {
        return rideTime;
    }

    public void setRideTime(int rideTime) {
        this.rideTime = rideTime;
    }



}
