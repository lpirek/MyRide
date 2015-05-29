package pl.ppteam.ahp.myride.common;

/**
 * Created by Łukasz on 2015-05-28.
 */
public class RideCompare extends Compare{

    private Ride ride1;
    private Ride ride2;

    public RideCompare(Ride ride1, Ride ride2) {
        this.ride1 = ride1;
        this.ride2 = ride2;
        this.wage = Wage.W1;
        this.direction = Direction.Equals;
    }

    public Ride getRide1() {
        return ride1;
    }

    public void setRide1(Ride ride1) {
        this.ride1 = ride1;
    }

    public Ride getRide2() {
        return ride2;
    }

    public void setRide2(Ride ride2) {
        this.ride2 = ride2;
    }

}
