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

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import pl.ppteam.ahp.myride.R;
import pl.ppteam.ahp.myride.adapter.MeansOfTransportAdapter;
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

    private MeansOfTransportAdapter adapter;

    private Calendar calendarFrom = Calendar.getInstance();
    private Calendar calendarTo = Calendar.getInstance();

    private ViewHolder holder;

    private int rideTime;

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

        this.setTitle(
                BaseController.getInstance().getRideQuery().getFromCity().getName() + " >> " +
                        BaseController.getInstance().getRideQuery().getToCity().getName());
        //Konstruowanie dialogu
        setContentView(R.layout.dialog_add_ride);

        holder = new ViewHolder();

        holder.btn_confirm = (Button) this.findViewById(R.id.dialog_newride_btn_confirm);
        holder.edt_cost_ride = (EditText) this.findViewById(R.id.edt_cost_ride);
        holder.edt_start_date = (EditText) this.findViewById(R.id.edt_start_date_ride);
        holder.edt_end_date = (EditText) this.findViewById(R.id.edt_end_date_ride);
        holder.time_travel = (TextView) this.findViewById(R.id.item_time_travel);
        holder.spn_typ = (Spinner) this.findViewById(R.id.spn_typ_transport);
        holder.edt_cost_ride.setText("0.00");

        adapter = new MeansOfTransportAdapter(activity, Arrays.asList(MeansOfTransport.values()));
        holder.spn_typ.setAdapter(adapter);

        holder.edt_start_date.setOnClickListener(this);
        holder.edt_start_date.setOnFocusChangeListener(this);

        holder.edt_end_date.setOnClickListener(this);
        holder.edt_end_date.setOnFocusChangeListener(this);

        setCalendarsDefaultTime();

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

    private void setCalendarsDefaultTime() {

        DateTime startDate = new DateTime(calendarFrom.getTime());
        DateTime endDate = new DateTime(calendarTo.getTime());

        startDate = startDate.plusHours(1).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(1);
        endDate = endDate.plusHours(2).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(1);

        calendarFrom.setTime(startDate.toDate());
        calendarTo.setTime(endDate.toDate());
    }

    private boolean validate() {
        City cityFrom = BaseController.getInstance().getRideQuery().getFromCity();
        City cityTo = BaseController.getInstance().getRideQuery().getToCity();
        MeansOfTransport typ = (MeansOfTransport)holder.spn_typ.getSelectedItem();
        Date startDate = calendarFrom.getTime();
        Date endDate = calendarTo.getTime();
        double price = 0;
        boolean toilet = false;

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
        holder.edt_start_date.setText(MessageFormat.format("{0} g.{1}",
                new SimpleDateFormat("dd-MM-yyyy").format(calendarFrom.getTime()), new SimpleDateFormat("HH:mm").format(calendarFrom.getTime())));
    }

    private void updateDateEnd() {
        holder.edt_end_date.setText(MessageFormat.format("{0} g.{1}",
                new SimpleDateFormat("dd-MM-yyyy").format(calendarTo.getTime()), new SimpleDateFormat("HH:mm").format(calendarTo.getTime())));
    }

    private void updateRideTime() {
        DateTime startDate = new DateTime(calendarFrom.getTime());
        DateTime endDate = new DateTime(calendarTo.getTime());

        Minutes minutes = Minutes.minutesBetween(startDate, endDate);
        rideTime = minutes.getMinutes();

        if (rideTime < 0) {
            rideTime = 0;
        }

        int hours = rideTime / 60;
        int minuts = rideTime - (hours * 60);

        holder.time_travel.setText(MessageFormat.format("{0}h {1}", hours, minuts != 0 ? minuts : ""));
    }

    public class ViewHolder {
        Button btn_confirm;
        EditText edt_cost_ride;
        EditText edt_start_date;
        EditText edt_end_date;
        TextView time_travel;
        Spinner spn_typ;
    }
}
