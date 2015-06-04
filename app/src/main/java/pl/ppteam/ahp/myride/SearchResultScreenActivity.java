package pl.ppteam.ahp.myride;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.view.MenuInflater;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import pl.ppteam.ahp.myride.adapter.RideAdapter;
import pl.ppteam.ahp.myride.common.MeansOfTransport;
import pl.ppteam.ahp.myride.common.Ride;
import pl.ppteam.ahp.myride.controller.BaseController;
import pl.ppteam.ahp.myride.dialog.AddRideDialog;
import pl.ppteam.ahp.myride.dialog.IDialogListener;
import pl.ppteam.ahp.myride.manager.SearchResultScreenManager;
import pl.ppteam.ahp.myride.query.CriteriumQuery;
import pl.ppteam.ahp.myride.query.RideQuery;
import pl.ppteam.ahp.myride.tool.Logger;


public class SearchResultScreenActivity extends ActionBarActivity implements View.OnClickListener, OnItemClickListener, IDialogListener, OnItemSelectedListener{

    private static final int DIALOG_CODE_ADD_RIDE = 0;

    private SearchResultScreenManager manager;
    private List<Ride> rideList;
    private MeansOfTransport typ;
    private RideAdapter adapter;
    private RideQuery query;

    private AddRideDialog dialog;

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
        getMenuInflater().inflate(R.menu.main_add_ride, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_ride:

                addRide();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

    private void addRide()
    {



        dialog = new AddRideDialog(this, DIALOG_CODE_ADD_RIDE, query, rideList,typ);
        dialog.setListener(this);
        dialog.show();

        dialog.holder.spn_typ.setAdapter(new ArrayAdapter<MeansOfTransport>(this, android.R.layout.simple_list_item_1, MeansOfTransport.values()));
        dialog.holder.spn_typ.setOnItemSelectedListener(this);


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

    @Override
    public void onPositiveClicked(int code) {
        switch (code) {
            case DIALOG_CODE_ADD_RIDE:
                adapter.notifyDataSetChanged();
                break;
            default:
                Logger.info("Unkown action source!");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        dialog.holder.spn_typ.setSelection(position);
        typ = (MeansOfTransport ) parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



}
