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
    private int cityFrom;

    @Column(name = "CityTo", notNull = true, onNullConflict = Column.ConflictAction.FAIL)
    private int cityTo;

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
        this.cityFrom = cityFrom.getId();
        this.cityTo = cityTo.getId();
        this.date = new Date();
    }

    public RideQueryDb(int cityFrom, int cityTo) {
        super();
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.date = new Date();
    }

    public int getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(int cityFrom) {
        this.cityFrom = cityFrom;
    }

    public int getCityTo() {
        return cityTo;
    }

    public void setCityTo(int cityTo) {
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
