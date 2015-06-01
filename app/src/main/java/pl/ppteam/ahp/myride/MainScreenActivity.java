package pl.ppteam.ahp.myride;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import pl.ppteam.ahp.myride.common.City;
import pl.ppteam.ahp.myride.controller.BaseController;
import pl.ppteam.ahp.myride.manager.MainScreenManager;
import pl.ppteam.ahp.myride.query.CityQuery;
import pl.ppteam.ahp.myride.query.RideQuery;
import pl.ppteam.ahp.myride.tool.Logger;


public class MainScreenActivity extends ActionBarActivity implements View.OnClickListener, View.OnFocusChangeListener {

    MainScreenManager manager;

    private List<City> cityList;
    private List<String> cityNames;

    private Calendar myCalendar = Calendar.getInstance();

    private CityQuery fromCityQuery;
    private CityQuery toCityQuery;

    //Components
    private Button btn_search;
    private AutoCompleteTextView edt_from;
    private AutoCompleteTextView edt_to;
    private EditText edt_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        manager = new MainScreenManager();

        DateTime today = new DateTime();
        today = today.withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(1);

        loadComponents();
        loadData();

        setListeners();
    }

    private void loadComponents() {
        btn_search = (Button) this.findViewById(R.id.main_screen_btn_search);
        edt_from = (AutoCompleteTextView) this.findViewById(R.id.main_screen_edt_from);
        edt_to = (AutoCompleteTextView) this.findViewById(R.id.main_screen_edt_to);
        edt_date = (EditText) this.findViewById(R.id.main_screen_edt_date);
    }

    private void loadData() {
        //cityList = manager.getCityList();
        cityNames = manager.getCityNames();

        fromCityQuery = new CityQuery();
        toCityQuery  = new CityQuery();

        ArrayAdapter<String> adapterFrom = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, cityNames);
        edt_from.setAdapter(adapterFrom);

        ArrayAdapter<String> adapterTo = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, cityNames);
        edt_to.setAdapter(adapterTo);

        updateDate();
    }

    private void updateDate() {
        edt_date.setText(new SimpleDateFormat("yyyy-MM-dd").format(myCalendar.getTime()));
    }

    private void setListeners() {
        edt_date.setOnClickListener(this);
        edt_date.setOnFocusChangeListener(this);
        btn_search.setOnClickListener(this);
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
