package pl.ppteam.ahp.myride.common;

import java.text.MessageFormat;
import java.util.Date;

/**
 * Created by Łukasz on 2015-05-22.
 */
public class Ride extends Item{

    private MeansOfTransport transportType;

    private String url;         //ewentualny odnośnik do strony ze źródłem

    private City fromCity;
    private City toCity;

    private double price;
    private boolean toilet;     //czy pojazd wyposażony jest w toaletę

    private Date startDate;
    private Date endDate;
    private int rideTime;       //długość wycieczki w minutach

    private double rankingValue;
    private int rankingPosition;

    //Zmienne dla projektu
    private boolean selected;

    public Ride() {

    }

    public Ride(MeansOfTransport transportType, City fromCity, City toCity, double price, boolean toilet,
                Date startDate, Date endDate, int rideTime) {
        this.transportType = transportType;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.price = price;
        this.toilet = toilet;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rideTime = rideTime;
    }

    public Ride(int id, MeansOfTransport transportType, City fromCity, City toCity, double price, boolean toilet,
                Date startDate, Date endDate, int rideTime) {
        this.id = id;
        this.transportType = transportType;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.price = price;
        this.toilet = toilet;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rideTime = rideTime;
    }

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

    public String getFormatRideTime() {
        int hours = rideTime / 60;
        int minuts = rideTime - (hours * 60);

        return MessageFormat.format("{0}h {1}", hours, minuts != 0 ? minuts : "");
    }

    public void setRideTime(int rideTime) {
        this.rideTime = rideTime;
    }

    public double getRankingValue() {
        return rankingValue;
    }

    public void setRankingValue(double rankingValue) {
        this.rankingValue = rankingValue;
    }

    public int getRankingPosition() {
        return rankingPosition;
    }

    public void setRankingPosition(int rankingPosition) {
        this.rankingPosition = rankingPosition;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


}
