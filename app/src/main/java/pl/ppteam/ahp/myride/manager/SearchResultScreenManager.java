package pl.ppteam.ahp.myride.manager;

import java.util.List;

import pl.ppteam.ahp.myride.common.Ride;
import pl.ppteam.ahp.myride.query.RideQuery;

/**
 * Created by £ukasz on 2015-05-23.
 */
public class SearchResultScreenManager {


    public List<Ride> getRideList(RideQuery query) {
        List<Ride> result = null;

        BaseManager manager = BaseManager.getInstance();
        result = manager.getRideList(query);

        return result;
    }
}
