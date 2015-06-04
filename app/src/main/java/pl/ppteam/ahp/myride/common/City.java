package pl.ppteam.ahp.myride.common;

import java.util.List;

/**
 * Created by  Łukasz on 2015-05-22.
 */
public class City extends Item{

    private String name;
    private String symbol;

    private List<PostCode> postCode;
    private double latitude; //szerokość geograficzna
    private double longitude; //długość geograficzna

    public City() {

    }

    public City(String name, String symbol, double latitude, double longitude) {
        this.name = name;
        this.symbol = symbol;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public City(int id, String name, String symbol, double latitude, double longitude) {
        this.id = id;
        this.symbol = symbol;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<PostCode> getPostCode() {
        return postCode;
    }

}
