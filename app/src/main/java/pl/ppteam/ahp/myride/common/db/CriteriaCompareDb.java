package pl.ppteam.ahp.myride.common.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import pl.ppteam.ahp.myride.common.Criterium;
import pl.ppteam.ahp.myride.common.Direction;
import pl.ppteam.ahp.myride.common.Wage;

/**
 * Created by Łukasz on 2015-05-29.
 */

@Table(name = "CriteriaCompare")
public class CriteriaCompareDb extends Model {

    @Column(name = "Criterium1", notNull = true, onNullConflict = Column.ConflictAction.FAIL)
    private String criterium1;

    @Column(name = "Criterium2", notNull = true, onNullConflict = Column.ConflictAction.FAIL)
    private String criterium2;

    @Column(name = "Wage", notNull = true, onNullConflict = Column.ConflictAction.FAIL)
    private Wage wage;

    @Column(name = "Direction", notNull = true, onNullConflict = Column.ConflictAction.FAIL)
    private Direction direction;

    public CriteriaCompareDb() {
        super();
    }

    public CriteriaCompareDb(Criterium criterium1, Criterium criterium2) {
        super();
        this.criterium1 = criterium1.getSymbol();
        this.criterium2 = criterium2.getSymbol();
    }

    public CriteriaCompareDb(String criterium1, String criterium2, Wage wage, Direction direction) {
        super();
        this.criterium1 = criterium1;
        this.criterium2 = criterium2;
        this.wage = wage;
        this.direction = direction;
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


    public static CriteriaCompareDb getSearchQuery(Criterium c1, Criterium c2) {
        return new Select()
                .from(CriteriaCompareDb.class)
                .where("(Criterium1 = ? AND Criterium2 = ?) OR (Criterium1 = ? AND Criterium2 = ?)",
                        c1.getId(), c2.getId(), c2.getId(), c1.getId())
                .executeSingle();
    }

}
