package pl.ppteam.ahp.myride.manager;

import java.util.List;

import pl.ppteam.ahp.myride.common.Criterium;
import pl.ppteam.ahp.myride.query.CriteriumQuery;

/**
 * Created by Łukasz on 2015-05-26.
 */
public class ChooseCriteriaScreenManager {

    public List<Criterium> getCriteriumList(CriteriumQuery query) {
        List<Criterium> result = null;

        BaseManager manager = BaseManager.getInstance();
        result = manager.getCriteriumList(query);

        return result;
    }
}
