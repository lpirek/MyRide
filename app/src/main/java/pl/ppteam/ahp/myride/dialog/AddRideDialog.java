package pl.ppteam.ahp.myride.dialog;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.DateTime;
import org.joda.time.Minutes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import pl.ppteam.ahp.myride.R;
import pl.ppteam.ahp.myride.common.City;
import pl.ppteam.ahp.myride.common.MeansOfTransport;
import pl.ppteam.ahp.myride.common.Ride;
import pl.ppteam.ahp.myride.controller.BaseController;
import pl.ppteam.ahp.myride.query.RideQuery;
import pl.ppteam.ahp.myride.tool.Logger;

/**
 * Created by Piotr Płaczek on 2015-06-03.
 */
public class AddRideDialog extends MainDialog implements View.OnClickListener, View.OnFocusChangeListener{

    private Ride ride;
    private Activity activity;

    private ArrayAdapter<MeansOfTransport> adapter;

    private Calendar calendarFrom = Calendar.getInstance();
    private Calendar calendarTo = Calendar.getInstance();

    private ViewHolder holder;

    public AddRideDialog(Activity activity, int code) {
        super(activity, code);
        this.activity = activity;
    }

    public Ride getAddedRide() {
        return ride;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Konstruowanie dialogu
        setContentView(R.layout.dialog_add_ride);

        holder = new ViewHolder();

        holder.btn_confirm = (Button) this.findViewById(R.id.dialog_newride_btn_confirm);
        holder.edt_from_ride = (EditText) this.findViewById(R.id.edt_new_ride_from);
        holder.edt_to_ride = (EditText) this.findViewById(R.id.edt_new_ride_to);
        holder.edt_cost_ride = (EditText) this.findViewById(R.id.edt_cost_ride);
        holder.edt_start_date = (EditText) this.findViewById(R.id.edt_start_date_ride);
        holder.edt_end_date = (EditText) this.findViewById(R.id.edt_end_date_ride);
        holder.edt_time_travel = (EditText) this.findViewById(R.id.edt_time_travel);
        holder.spn_typ = (Spinner) this.findViewById(R.id.spn_typ_transport);

        holder.edt_from_ride.setText(BaseController.getInstance().getRideQuery().getFromCity().getName());
        holder.edt_to_ride.setText(BaseController.getInstance().getRideQuery().getToCity().getName());
        holder.edt_cost_ride.setText("0.00");

        adapter = new ArrayAdapter<MeansOfTransport>(activity, android.R.layout.simple_list_item_1, MeansOfTransport.values());
        holder.spn_typ.setAdapter(adapter);

        holder.edt_start_date.setOnClickListener(this);
        holder.edt_start_date.setOnFocusChangeListener(this);

        holder.edt_end_date.setOnClickListener(this);
        holder.edt_end_date.setOnFocusChangeListener(this);

        updateDateStart();
        updateDateEnd();
        updateRideTime();

        holder.btn_confirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (validate()) {
                    if (listener != null) {
                        listener.onPositiveClicked(code);
                    }

                    AddRideDialog.this.dismiss();
                }
                else {
                    Toast.makeText(activity, "Niepoprawne wartości", Toast.LENGTH_SHORT).show();
                }
            }

        });


    }

    private boolean validate() {
        City cityFrom = BaseController.getInstance().getRideQuery().getFromCity();
        City cityTo = BaseController.getInstance().getRideQuery().getToCity();
        MeansOfTransport typ = (MeansOfTransport)holder.spn_typ.getSelectedItem();
        Date startDate = calendarFrom.getTime();
        Date endDate = calendarTo.getTime();
        double price = 0;
        boolean toilet = false;
        int rideTime = 0;

        //Walidacja dat
        if (endDate.before(startDate) || endDate.equals(startDate)) {
            return false;
        }

        //Walidacja ceny przejazdu
        String priceRide = holder.edt_cost_ride.getText().toString();

        if(priceRide == null || priceRide.isEmpty()) {
            return false;
        }
        else {
            try {
                price = Double.parseDouble(priceRide);
            }
            catch (Exception e) {
                return false;
            }
        }

        String timeRide = holder.edt_time_travel.getText().toString();
        rideTime = Integer.parseInt(timeRide);

        ride = new Ride(typ, cityFrom, cityTo, price, toilet, startDate, endDate, rideTime);

        return true;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.edt_start_date_ride:
                openDatePickerStart();
                break;
            case R.id.edt_end_date_ride:
                openDatePickerEnd();
                break;
            default:
                Logger.info("Unkown action source!");
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch(v.getId()) {
            case R.id.edt_start_date_ride:
                if (hasFocus) openDatePickerStart();
                break;
            case R.id.edt_end_date_ride:
                if (hasFocus) openDatePickerEnd();
                break;
            default:
                Logger.info("Unkown action source!");
        }
    }

    private void openDatePickerStart() {
        new DatePickerDialog(activity, dateStart,
                calendarFrom.get(Calendar.YEAR),
                calendarFrom.get(Calendar.MONTH),
                calendarFrom.get(Calendar.DAY_OF_MONTH)).show();
    }

    DatePickerDialog.OnDateSetListener dateStart = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendarFrom.set(Calendar.YEAR, year);
            calendarFrom.set(Calendar.MONTH, monthOfYear);
            calendarFrom.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            updateDateStart();
            updateRideTime();
        }

    };

    private void openDatePickerEnd() {
        new DatePickerDialog(activity, dateEnd,
                calendarTo.get(Calendar.YEAR),
                calendarTo.get(Calendar.MONTH),
                calendarTo.get(Calendar.DAY_OF_MONTH)).show();
    }

    DatePickerDialog.OnDateSetListener dateEnd = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendarTo.set(Calendar.YEAR, year);
            calendarTo.set(Calendar.MONTH, monthOfYear);
            calendarTo.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            updateDateEnd();
            updateRideTime();
        }

    };

    private void updateDateStart() {
        holder.edt_start_date.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(calendarFrom.getTime()));
    }

    private void updateDateEnd() {
        holder.edt_end_date.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(calendarTo.getTime()));
    }

    private void updateRideTime() {
        int difference = 0;

        DateTime startDate = new DateTime(calendarFrom.getTime());
        DateTime endDate = new DateTime(calendarTo.getTime());

        Minutes minutes = Minutes.minutesBetween(startDate, endDate);
        difference = minutes.getMinutes();

        if (difference < 0) {
            difference = 0;
        }

        holder.edt_time_travel.setText(String.valueOf(difference));
    }

    public class ViewHolder {
        Button btn_confirm;
        EditText edt_from_ride;
        EditText edt_to_ride;
        EditText edt_cost_ride;
        EditText edt_start_date;
        EditText edt_end_date;
        EditText edt_time_travel;
        Spinner spn_typ;
    }
}
