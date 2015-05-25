package pl.ppteam.ahp.myride;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import pl.ppteam.ahp.myride.manager.SearchResultScreenManager;
import pl.ppteam.ahp.myride.query.RideQuery;


public class SearchResultScreenActivity extends ActionBarActivity {

    public static final String QUERY_KEY = "query";

    private SearchResultScreenManager manager;

    private RideQuery query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result_screen);

        manager = new SearchResultScreenManager();

        Intent intent = getIntent();
        query = (RideQuery)intent.getSerializableExtra(QUERY_KEY);

        loadComponents();
        loadData();

        setListeners();
    }

    private void loadComponents() {
    }

    private void loadData() {
    }

    private void setListeners() {
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
}
