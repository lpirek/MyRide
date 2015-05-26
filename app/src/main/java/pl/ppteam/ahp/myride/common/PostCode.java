package pl.ppteam.ahp.myride.common;

/**
 * Created by £ukasz on 2015-05-23.
 */
public class PostCode extends Item {

    private City city;
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

}
