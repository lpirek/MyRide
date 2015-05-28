package pl.ppteam.ahp.myride.common;

/**
 * Created by Łukasz on 2015-05-28.
 */
public class RideCompare {

    private Ride ride1;
    private Ride ride2;
    private Wage wage;
    private Direction direction;

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

    public Wage getWage() {
        return wage;
    }

    public void setWage(Wage wage) {
        this.wage = wage;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }


}
