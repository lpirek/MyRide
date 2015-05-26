package pl.ppteam.ahp.myride.common;

import java.util.List;

/**
 * Created by £ukasz on 2015-05-22.
 */
public class City extends Item{

    private String name;

    private List<PostCode> postCode;
    private double latitude; //szerokoœæ geograficzna
    private double longitude; //d³ugoœæ geograficzna

    public City() {

    }

    public City(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public City(int id, String name, double latitude, double longitude) {
        this.id = id;
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
