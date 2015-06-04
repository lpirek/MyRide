package pl.ppteam.ahp.myride.common.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.Date;
import java.util.List;

import pl.ppteam.ahp.myride.common.City;

/**
 * Created by Łukasz on 2015-05-29.
 */

@Table(name = "RideQuery")
public class RideQueryDb extends Model {

    @Column(name = "CityFrom", notNull = true, onNullConflict = Column.ConflictAction.FAIL)
    private String cityFrom;

    @Column(name = "CityTo", notNull = true, onNullConflict = Column.ConflictAction.FAIL)
    private String cityTo;

    @Column(name = "Price", notNull = false, onNullConflict = Column.ConflictAction.FAIL)
    private double price;

    @Column(name = "Date", notNull = true, onNullConflict = Column.ConflictAction.FAIL)
    private Date date;

    @Column(name = "StartDate", notNull = false, onNullConflict = Column.ConflictAction.FAIL)
    private Date startDate;

    @Column(name = "EndDate", notNull = false, onNullConflict = Column.ConflictAction.FAIL)
    private Date endDate;

    @Column(name = "RideTime", notNull = false, onNullConflict = Column.ConflictAction.FAIL)
    private int rideTime;

    public RideQueryDb() {
        super();
    }

    public RideQueryDb(City cityFrom, City cityTo) {
        super();
        this.cityFrom = cityFrom.getSymbol();
        this.cityTo = cityTo.getSymbol();
        this.date = new Date();
    }

    public RideQueryDb(String cityFrom, String cityTo) {
        super();
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.date = new Date();
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public static List<RideQueryDb> getFirstRideQueries(int limit) {
        return new Select()
                .from(RideQueryDb.class)
                .orderBy("Date DESC")
                .limit(limit)
                .execute();
    }

}
