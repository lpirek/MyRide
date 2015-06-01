package pl.ppteam.ahp.myride;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import pl.ppteam.ahp.myride.adapter.CriteriaAdapter;
import pl.ppteam.ahp.myride.adapter.CriteriaCompareAdapter;
import pl.ppteam.ahp.myride.common.CriteriaCompare;
import pl.ppteam.ahp.myride.common.Criterium;
import pl.ppteam.ahp.myride.controller.BaseController;
import pl.ppteam.ahp.myride.dialog.CompareCriteriaDialog;
import pl.ppteam.ahp.myride.dialog.IDialogListener;
import pl.ppteam.ahp.myride.manager.ChooseCriteriaScreenManager;
import pl.ppteam.ahp.myride.manager.CompareCriteriaScreenManager;
import pl.ppteam.ahp.myride.query.CriteriumQuery;
import pl.ppteam.ahp.myride.tool.Logger;


public class CompareCriteriaScreenActivity extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener, IDialogListener {

    private static final int DIALOG_CODE_COMPARE_CRITERIA = 0;

    private CompareCriteriaScreenManager manager;

    private List<CriteriaCompare> criteriaCompareList;

    private CriteriaCompareAdapter adapter;

    //Components
    private Button btn_confirm;
    private ListView lv_criteria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_criteria_screen);

        manager = new CompareCriteriaScreenManager();

        Intent intent = getIntent();

        loadComponents();
        loadData();

        setListeners();
    }

    private void loadComponents() {
        btn_confirm = (Button) this.findViewById(R.id.compare_criteria_screen_btn_confirm);
        lv_criteria = (ListView) this.findViewById(R.id.compare_criteria_screen_lv);
    }

    private void loadData() {
        criteriaCompareList = BaseController.getInstance().getSelectedCriteriaCompare();
        manager.fillData(criteriaCompareList);

        adapter = new CriteriaCompareAdapter(this, criteriaCompareList);
        lv_criteria.setAdapter(adapter);
    }

    private void setListeners() {
        btn_confirm.setOnClickListener(this);
        lv_criteria.setOnItemClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_compare_criteria, menu);
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
            case R.id.compare_criteria_screen_btn_confirm:
                confirmComparation();
                break;
            default:
                Logger.info("Unkown action source!");
        }
    }

    private void confirmComparation() {

        boolean result = BaseController.getInstance().confirmCriteriaCompare();
        manager.saveData(criteriaCompareList);

        if (!result) {
            new AlertDialog.Builder(this)
                    .setMessage("Zaproponowane dane nie są poprawne. \nCzy chcesz przejść dalej?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            navigateToNext();
                        }})

                    .setNegativeButton(android.R.string.no, null).show();
        }
        else {
            navigateToNext();
        }

    }

    private void navigateToNext() {
        Intent intent = new Intent(this, CompareRideScreenActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        CriteriaCompare selectedCompare = (CriteriaCompare) adapter.getItem(position);

        CompareCriteriaDialog dialog = new CompareCriteriaDialog(this, DIALOG_CODE_COMPARE_CRITERIA, selectedCompare);
        dialog.setListener(this);
        dialog.show();

    }

    @Override
    public void onPositiveClicked(int code) {
        switch (code) {
            case DIALOG_CODE_COMPARE_CRITERIA:
                adapter.notifyDataSetChanged();
                break;
            default:
                Logger.info("Unkown action source!");
        }
    }

}
