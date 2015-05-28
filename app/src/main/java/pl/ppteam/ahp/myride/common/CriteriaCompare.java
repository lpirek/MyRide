package pl.ppteam.ahp.myride.common;

/**
 * Created by Łukasz on 2015-05-28.
 */
public class CriteriaCompare {

    private Criterium criterium1;
    private Criterium criterium2;
    private Wage wage;
    private Direction direction;

    public CriteriaCompare(Criterium criterium1, Criterium criterium2) {
        this.criterium1 = criterium1;
        this.criterium2 = criterium2;
        this.wage = Wage.W1;
        this.direction = Direction.Equals;
    }

    public Criterium getCriterium1() {
        return criterium1;
    }

    public void setCriterium1(Criterium criterium1) {
        this.criterium1 = criterium1;
    }

    public Criterium getCriterium2() {
        return criterium2;
    }

    public void setCriterium2(Criterium criterium2) {
        this.criterium2 = criterium2;
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
