package pl.ppteam.ahp.myride;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import pl.ppteam.ahp.myride.adapter.CriteriaAdapter;
import pl.ppteam.ahp.myride.adapter.RankingAdapter;
import pl.ppteam.ahp.myride.common.Ride;
import pl.ppteam.ahp.myride.controller.BaseController;
import pl.ppteam.ahp.myride.manager.ChooseCriteriaScreenManager;
import pl.ppteam.ahp.myride.manager.ResultRankingScreenManager;
import pl.ppteam.ahp.myride.query.CriteriumQuery;


public class ResultRankingScreenActivity extends ActionBarActivity {

    private ResultRankingScreenManager manager;

    private List<Ride> rideList;

    private RankingAdapter adapter;

    //Components
    private ListView lv_ranking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_ranking_screen);

        manager = new ResultRankingScreenManager();

        Intent intent = getIntent();

        loadComponents();
        loadData();

    }

    private void loadComponents() {
        lv_ranking = (ListView) this.findViewById(R.id.result_ranking_screen_lv);
    }

    private void loadData() {
        rideList = BaseController.getInstance().getRankingRide();

        adapter = new RankingAdapter(this, rideList);
        lv_ranking.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_result_ranking_screen, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
