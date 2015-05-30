package pl.ppteam.ahp.myride;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import pl.ppteam.ahp.myride.adapter.CriteriaCompareAdapter;
import pl.ppteam.ahp.myride.adapter.RideCompareAdapter;
import pl.ppteam.ahp.myride.common.CriteriaCompare;
import pl.ppteam.ahp.myride.common.Criterium;
import pl.ppteam.ahp.myride.common.RideCompare;
import pl.ppteam.ahp.myride.controller.BaseController;
import pl.ppteam.ahp.myride.dialog.CompareRideDialog;
import pl.ppteam.ahp.myride.dialog.IDialogListener;
import pl.ppteam.ahp.myride.manager.CompareCriteriaScreenManager;
import pl.ppteam.ahp.myride.manager.CompareRideScreenManager;
import pl.ppteam.ahp.myride.tool.Logger;


public class CompareRideScreenActivity extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener, IDialogListener {

    private static final int DIALOG_CODE_COMPARE_RIDE = 0;

    private CompareRideScreenManager manager;

    private Criterium currentCriterium;
    private List<RideCompare> rideCompareList;

    private RideCompareAdapter adapter;

    //Components
    private Button btn_confirm;
    private ListView lv_ride;
    private View headerView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_ride_screen);

        manager = new CompareRideScreenManager();

        Intent intent = getIntent();
        currentCriterium = BaseController.getInstance().getCurrentCriterium();

        loadComponents();
        loadData();

        setListeners();
    }

    private void loadComponents() {
        btn_confirm = (Button) this.findViewById(R.id.compare_ride_screen_btn_confirm);
        lv_ride = (ListView) this.findViewById(R.id.compare_ride_screen_lv);
        textView = (TextView) View.inflate(this, R.layout.header, null);
        textView.setText(currentCriterium.getName());
        lv_ride.addHeaderView(textView, null, false);
    }

    private void loadData() {
        rideCompareList = BaseController.getInstance().getSelectedRidesCompare(currentCriterium);

        adapter = new RideCompareAdapter(this, rideCompareList);
        lv_ride.setAdapter(adapter);
    }

    private void setListeners() {
        btn_confirm.setOnClickListener(this);
        lv_ride.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_compare_ride, menu);
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
            case R.id.compare_ride_screen_btn_confirm:
                confirmComparation();
                break;
            default:
                Logger.info("Unkown action source!");
        }
    }

    private void confirmComparation() {

        BaseController.getInstance().confirmRidesCompare(currentCriterium);

        if (BaseController.getInstance().hasNextCriterium()) {

            BaseController.getInstance().nextCriterium();

            Intent intent = new Intent(this, CompareRideScreenActivity.class);
            startActivity(intent);
        }
        else {
            BaseController.getInstance().calculateRankingRide();

            Intent intent = new Intent(this, ResultRankingScreenActivity.class);
            startActivity(intent);
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //Tutaj otwarcie dialogu
        RideCompare selectedCompare = (RideCompare) adapter.getItem(position-1);

        CompareRideDialog dialog = new CompareRideDialog(this, DIALOG_CODE_COMPARE_RIDE, selectedCompare);
        dialog.setListener(this);
        dialog.show();
    }

    @Override
    public void onPositiveClicked(int code) {
        switch (code) {
            case DIALOG_CODE_COMPARE_RIDE:
                adapter.notifyDataSetChanged();
                break;
            default:
                Logger.info("Unkown action source!");
        }
    }

    @Override
    public void onBackPressed() {
        BaseController.getInstance().prevCriterium();
        super.onBackPressed();
    }
}
