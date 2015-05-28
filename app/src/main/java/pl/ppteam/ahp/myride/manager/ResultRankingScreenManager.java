package pl.ppteam.ahp.myride.manager;

import java.util.List;
import pl.ppteam.ahp.myride.common.Ride;
import pl.ppteam.ahp.myride.controller.BaseController;

/**
 * Created by Łukasz on 2015-05-26.
 */
public class ResultRankingScreenManager {

    public List<Ride> getRankingList() {
        List<Ride> result = null;

        BaseController manager = BaseController.getInstance();
        result = manager.getRankingRide();

        return result;
    }

}
