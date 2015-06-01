package pl.ppteam.ahp.myride.manager;

import java.util.List;

import pl.ppteam.ahp.myride.common.CriteriaCompare;
import pl.ppteam.ahp.myride.common.db.CriteriaCompareDb;

/**
 * Created by Łukasz on 2015-05-26.
 */
public class CompareCriteriaScreenManager {

    public void fillData(List<CriteriaCompare> criteriaCompareList) {

        for(CriteriaCompare compare : criteriaCompareList) {

            CriteriaCompareDb compareDb = CriteriaCompareDb.getSearchQuery(compare.getCriterium1(), compare.getCriterium2());

            if (compareDb != null) {
                compare.setDirection(compareDb.getDirection());
                compare.setWage(compareDb.getWage());
            }
        }

    }

    public void saveData(List<CriteriaCompare> criteriaCompareList) {

        for(CriteriaCompare compare : criteriaCompareList) {

            CriteriaCompareDb compareDb = CriteriaCompareDb.getSearchQuery(compare.getCriterium1(), compare.getCriterium2());

            if (compareDb == null) {
                compareDb = new CriteriaCompareDb(compare.getCriterium1(), compare.getCriterium2());
            }

            compareDb.setDirection(compare.getDirection());
            compareDb.setWage(compare.getWage());
            compareDb.save();
        }

    }

}
