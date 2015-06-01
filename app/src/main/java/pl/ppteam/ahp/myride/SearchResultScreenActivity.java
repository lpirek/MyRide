package pl.ppteam.ahp.myride;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import pl.ppteam.ahp.myride.adapter.RideAdapter;
import pl.ppteam.ahp.myride.common.Ride;
import pl.ppteam.ahp.myride.controller.BaseController;
import pl.ppteam.ahp.myride.manager.SearchResultScreenManager;
import pl.ppteam.ahp.myride.query.CriteriumQuery;
import pl.ppteam.ahp.myride.query.RideQuery;
import pl.ppteam.ahp.myride.tool.Logger;


public class SearchResultScreenActivity extends ActionBarActivity implements View.OnClickListener, OnItemClickListener{

    private SearchResultScreenManager manager;

    private List<Ride> rideList;

    private RideAdapter adapter;
    private RideQuery query;

    //Components
    private Button btn_confirm;
    private ListView lv_result;
    private ImageView img_empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result_screen);

        manager = new SearchResultScreenManager();

        Intent intent = getIntent();
        query = BaseController.getInstance().getRideQuery();

        loadComponents();
        loadData();

        setListeners();
    }

    private void loadComponents() {
        btn_confirm = (Button) this.findViewById(R.id.search_result_screen_btn_confirm);
        lv_result = (ListView) this.findViewById(R.id.search_result_screen_lv);
        img_empty = (ImageView) this.findViewById(R.id.search_result_screen_img);
    }

    private void loadData() {
        rideList = manager.getRideList(query);

        if (rideList.size() > 0) {
            adapter = new RideAdapter(this, rideList);
            lv_result.setAdapter(adapter);
        }
        else {
            btn_confirm.setVisibility(View.INVISIBLE);
            lv_result.setVisibility(View.INVISIBLE);
            img_empty.setVisibility(View.VISIBLE);
        }

    }

    private void setListeners() {
        btn_confirm.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_search_result_screen, menu);
        //return true;
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


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.search_result_screen_btn_confirm:
                chooseRide();
                break;
            default:
                Logger.info("Unkown action source!");
        }
    }

    private void chooseRide() {
        List<Ride> result = adapter.getSelectedRides();

        if (result.size() >= 3) {
            BaseController.getInstance().setSelectedRides(result);

            //Przejście dalej
            Intent intent = new Intent(this, ChooseCriteriaScreenActivity.class);
            startActivity(intent);

        }
        else
        {
            Toast.makeText(this, "Wybrałeś zbyt mało pozycji. Minimum 3", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


    }

}
