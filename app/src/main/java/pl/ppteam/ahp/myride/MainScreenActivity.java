package pl.ppteam.ahp.myride;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import pl.ppteam.ahp.myride.adapter.CriteriaCompareAdapter;
import pl.ppteam.ahp.myride.adapter.LastSearchAdapter;
import pl.ppteam.ahp.myride.common.City;
import pl.ppteam.ahp.myride.common.CriteriaCompare;
import pl.ppteam.ahp.myride.common.db.RideQueryDb;
import pl.ppteam.ahp.myride.controller.BaseController;
import pl.ppteam.ahp.myride.dialog.CompareCriteriaDialog;
import pl.ppteam.ahp.myride.manager.MainScreenManager;
import pl.ppteam.ahp.myride.query.CityQuery;
import pl.ppteam.ahp.myride.query.RideQuery;
import pl.ppteam.ahp.myride.tool.Logger;


public class MainScreenActivity extends ActionBarActivity implements View.OnClickListener, View.OnFocusChangeListener, AdapterView.OnItemClickListener {

    MainScreenManager manager;

    private List<RideQuery> rideQueries;
    private List<String> cityNames;

    private Calendar myCalendar = Calendar.getInstance();

    private CityQuery fromCityQuery;
    private CityQuery toCityQuery;

    private LastSearchAdapter adapter;

    //Components
    private Button btn_search;
    private AutoCompleteTextView edt_from;
    private AutoCompleteTextView edt_to;
    private EditText edt_date;
    private RelativeLayout layout_last_search;
    private ListView lv_last_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        manager = new MainScreenManager();

        loadComponents();
        loadData();

        setListeners();
    }

    private void loadComponents() {
        btn_search = (Button) this.findViewById(R.id.main_screen_btn_search);
        edt_from = (AutoCompleteTextView) this.findViewById(R.id.main_screen_edt_from);
        edt_to = (AutoCompleteTextView) this.findViewById(R.id.main_screen_edt_to);
        edt_date = (EditText) this.findViewById(R.id.main_screen_edt_date);
        layout_last_search = (RelativeLayout) this.findViewById(R.id.main_screen_layout_last_search);
        lv_last_search = (ListView) this.findViewById(R.id.main_screen_lv_last_search);
    }

    private void loadData() {
        //cityList = manager.getCityList();
        cityNames = manager.getCityNames();
        rideQueries = manager.getLastRideQueries();

        fromCityQuery = new CityQuery();
        toCityQuery  = new CityQuery();

        ArrayAdapter<String> adapterFrom = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, cityNames);
        edt_from.setAdapter(adapterFrom);

        ArrayAdapter<String> adapterTo = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, cityNames);
        edt_to.setAdapter(adapterTo);

        updateDate();

        if (rideQueries.size() > 0) {
            adapter = new LastSearchAdapter(this, rideQueries);
            lv_last_search.setAdapter(adapter);
        }
        else {
            layout_last_search.setVisibility(View.INVISIBLE);
            lv_last_search.setVisibility(View.INVISIBLE);
        }
    }

    private void updateDate() {
        edt_date.setText(new SimpleDateFormat("dd-MM-yyyy").format(myCalendar.getTime()));
    }

    private void setListeners() {
        edt_date.setOnClickListener(this);
        edt_date.setOnFocusChangeListener(this);
        btn_search.setOnClickListener(this);
        lv_last_search.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main_screen, menu);
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
            case R.id.main_screen_btn_search:
                searchRide();
                break;
            case R.id.main_screen_edt_date:
                openDatePicker();
                break;
            default:
                Logger.info("Unkown action source!");
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch(v.getId()) {
            case R.id.main_screen_edt_date:
                if (hasFocus) openDatePicker();
                break;
            default:
                Logger.info("Unkown action source!");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        RideQuery selectedRideQuery = (RideQuery) adapter.getItem(position);
        selectRideQuery(selectedRideQuery);
    }

    private void selectRideQuery(RideQuery rideQuery) {

        BaseController.getInstance().setRideQuery(rideQuery);

        Intent intent = new Intent(this, SearchResultScreenActivity.class);
        startActivity(intent);
    }

    private void searchRide() {

        fromCityQuery.setName(edt_from.getText().toString());
        toCityQuery.setName(edt_to.getText().toString());

        City fromCity = manager.getCity(fromCityQuery);
        City toCity = manager.getCity(toCityQuery);

        if (fromCity != null && toCity != null){

            DateTime dateTime = new DateTime(myCalendar.getTime());
            dateTime = dateTime.withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0);

            RideQuery query = new RideQuery();
            query.setFromCity(fromCity);
            query.setToCity(toCity);
            query.setStartDate(dateTime.toDate());

            BaseController.getInstance().setRideQuery(query);

            RideQueryDb queryDb = new RideQueryDb(fromCity, toCity);
            queryDb.setStartDate(dateTime.toDate());
            queryDb.save();

            Intent intent = new Intent(this, SearchResultScreenActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Nie udało się odnaleźć podanych miejscowości. Spróbuj jeszcze raz.", Toast.LENGTH_LONG).show();
        }
    }

    private void openDatePicker() {
        new DatePickerDialog(MainScreenActivity.this, date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDate();
        }

    };



}
