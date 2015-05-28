package pl.ppteam.ahp.myride;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
//Adapter dla Criterium
import pl.ppteam.ahp.myride.adapter.CriteriaAdapter;
import pl.ppteam.ahp.myride.adapter.RideAdapter;
import pl.ppteam.ahp.myride.common.Criterium;
import pl.ppteam.ahp.myride.controller.BaseController;
import pl.ppteam.ahp.myride.manager.ChooseCriteriaScreenManager;
import pl.ppteam.ahp.myride.manager.CompareCriteriaScreenManager;
import pl.ppteam.ahp.myride.query.CriteriumQuery;
import pl.ppteam.ahp.myride.tool.Logger;


public class ChooseCriteriaScreenActivity extends ActionBarActivity implements View.OnClickListener, OnItemClickListener{


    private ChooseCriteriaScreenManager manager;

    private List<Criterium> criteriumList;

    private CriteriaAdapter adapter;
    private CriteriumQuery query;

    //Components
    private Button btn_criteria_admitted;
    private ListView lv_criteria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_criteria_screen);

        manager = new ChooseCriteriaScreenManager();

        Intent intent = getIntent();
        query = new CriteriumQuery();

        loadComponents();
        loadData();

        setListeners();
    }

    private void loadComponents() {
        btn_criteria_admitted = (Button) this.findViewById(R.id.choose_criteria_btn_admitted);
        lv_criteria = (ListView) this.findViewById(R.id.choose_criteria_lv);
    }

    private void loadData() {
        criteriumList = manager.getCriteriumList(query);

        adapter = new CriteriaAdapter(this, criteriumList);
        lv_criteria.setAdapter(adapter);
    }

    private void setListeners() {
        btn_criteria_admitted.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_choose_criteria, menu);
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
            case R.id.choose_criteria_btn_admitted:
                chooseCriterium();
                break;
            default:
                Logger.info("Unkown action source!");
        }
    }

    private void chooseCriterium() {
        List<Criterium> result = adapter.getSelectCriteriums();

        if (result.size() >= 3) {
            BaseController.getInstance().setSelectedCriteriums(result);

            //Dalej
            Intent intent = new Intent(this, ResultRankingScreenActivity.class);
            startActivity(intent);

        } else {
            Toast.makeText(this, "Wybrałeś zbyt mało pozycji. Minimum 3.", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
