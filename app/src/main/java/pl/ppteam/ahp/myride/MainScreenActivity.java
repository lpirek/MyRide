package pl.ppteam.ahp.myride;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import pl.ppteam.ahp.myride.common.City;
import pl.ppteam.ahp.myride.manager.MainScreenManager;
import pl.ppteam.ahp.myride.query.CityQuery;
import pl.ppteam.ahp.myride.query.RideQuery;
import pl.ppteam.ahp.myride.tool.Logger;


public class MainScreenActivity extends ActionBarActivity implements View.OnClickListener {

    MainScreenManager manager;

    private List<City> cityList;

    private CityQuery fromCityQuery;
    private CityQuery toCityQuery;

    //Components
    private Button btn_search;
    private AutoCompleteTextView edt_from;
    private AutoCompleteTextView edt_to;

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
    }

    private void loadData() {
        cityList = manager.getCityList();

        fromCityQuery = new CityQuery();
        toCityQuery  = new CityQuery();
    }

    private void setListeners() {
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

            RideQuery query = new RideQuery();
            query.setFromCity(fromCity);
            query.setToCity(toCity);

            Intent intent = new Intent(this, SearchResultScreenActivity.class);
            intent.putExtra(SearchResultScreenActivity.QUERY_KEY, query);
            startActivity(intent);
        }
        else
        {
            //Wyœwietliæ komunikat o braku podanej miejscowoœci
        }
    }
}
