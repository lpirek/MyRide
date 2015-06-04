package pl.ppteam.ahp.myride.dialog;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import pl.ppteam.ahp.myride.R;
import pl.ppteam.ahp.myride.common.City;
import pl.ppteam.ahp.myride.common.MeansOfTransport;
import pl.ppteam.ahp.myride.common.Ride;
import pl.ppteam.ahp.myride.query.RideQuery;
import pl.ppteam.ahp.myride.tool.Logger;

/**
 * Created by Piotr Płaczek on 2015-06-03.
 */
public class AddRideDialog extends MainDialog implements View.OnClickListener, View.OnFocusChangeListener{

    private Ride ride;
    public ViewHolder holder;
    private RideQuery query;
    private Calendar myCalendar = Calendar.getInstance();
    private Activity activity;
    private List<Ride> list;
    private MeansOfTransport typ;

    public AddRideDialog(Activity activity, int code, RideQuery query, List<Ride> list, MeansOfTransport typ) {
        super(activity, code);
        this.activity = activity;
        this.query = query;
        this.list = list;
        this.typ = typ;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Konstruowanie dialogu
        setContentView(R.layout.dialog_add_ride);

        holder = new ViewHolder();

        holder.btn_confirm = (Button) this.findViewById(R.id.dialog_newride_btn_confirm);

        holder.from_ride = (TextView) this.findViewById(R.id.item_new_ride_from);
        holder.edt_from_ride = (EditText) this.findViewById(R.id.edt_new_ride_from);

        holder.to_ride = (TextView) this.findViewById(R.id.item_new_ride_to);
        holder.edt_to_ride = (EditText) this.findViewById(R.id.edt_new_ride_to);

        holder.tv_typ = (TextView) this.findViewById(R.id.item_typ_transport);
        holder.spn_typ = (Spinner) this.findViewById(R.id.spn_typ_transport);

        holder.cost_ride = (TextView) this.findViewById(R.id.item_cost_ride);
        holder.edt_cost_ride = (EditText) this.findViewById(R.id.edt_cost_ride);

        holder.start_date = (TextView) this.findViewById(R.id.item_start_date_ride);
        holder.edt_start_date = (EditText) this.findViewById(R.id.edt_start_date_ride);

        holder.end_date = (TextView) this.findViewById(R.id.item_end_date_ride);
        holder.edt_end_date = (EditText) this.findViewById(R.id.edt_end_date_ride);

        holder.time_travel = (TextView) this.findViewById(R.id.item_time_travel);
        holder.edt_time_travel = (EditText) this.findViewById(R.id.edt_time_travel);


        holder.edt_from_ride.setText(query.getFromCity().getName());
        holder.edt_to_ride.setText(query.getToCity().getName());

        holder.spn_typ = (Spinner)this.findViewById(R.id.spn_typ_transport);

        holder.edt_start_date.setOnClickListener(this);
        holder.edt_start_date.setOnFocusChangeListener(this);

        holder.edt_end_date.setOnClickListener(this);
        holder.edt_end_date.setOnFocusChangeListener(this);

        holder.edt_start_date.setText(new SimpleDateFormat("dd-MM-yyyy").format(query.getStartDate().getTime()));
        holder.edt_end_date.setText(new SimpleDateFormat("dd-MM-yyyy").format(query.getStartDate().getTime()));

        holder.btn_confirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (listener != null) {
                    setValue();
                    listener.onPositiveClicked(code);
                }
                AddRideDialog.this.dismiss();
            }

        });


    }

    private void setValue()
    {
        //MeansOfTransport typ = holder.spn_typ.getOnItemSelectedListener().onItemSelected();
        String priceRide = holder.edt_cost_ride.getText().toString();
        Double price;
        Date rideFrom = null;
        Date rideTo = null;
        long rideTime;


        try {
            rideFrom =  new SimpleDateFormat("yyyy-MM-dd").parse(holder.edt_start_date.getText().toString());
            rideTo =  new SimpleDateFormat("yyyy-MM-dd").parse(holder.edt_end_date.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(priceRide == null || priceRide.isEmpty()) {

            price = 0.0;

        } else {

            price = Double.parseDouble(priceRide);

        }
            if(rideFrom == null || rideTo == null) {

                rideTime = 0;

            } else {

                rideTime = rideTo.getTime() - rideFrom.getTime();
            }

            int time = (int) rideTime;

        list.add(list.size()+1,new Ride(list.size()+1,
                typ,
                query.getFromCity(),
                query.getToCity(),
                price,
                false,
                rideFrom,
                rideTo,
                time));
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
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    DatePickerDialog.OnDateSetListener dateStart = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDateStart();
        }

    };

    private void openDatePickerEnd() {
        new DatePickerDialog(activity, dateEnd,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    DatePickerDialog.OnDateSetListener dateEnd = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDateEnd();
        }

    };

    private void updateDateStart() {
        holder.edt_start_date.setText(new SimpleDateFormat("dd-MM-yyyy").format(myCalendar.getTime()));
    }

    private void updateDateEnd() {
        holder.edt_end_date.setText(new SimpleDateFormat("dd-MM-yyyy").format(myCalendar.getTime()));
    }

    public class ViewHolder {
        Button btn_confirm;

        TextView from_ride;
        TextView to_ride;

        EditText edt_from_ride;
        EditText edt_to_ride;

        TextView tv_typ;
        public Spinner spn_typ;

        TextView cost_ride;
        EditText edt_cost_ride;

        TextView start_date;
        public EditText edt_start_date;

        TextView end_date;
        EditText edt_end_date;

        TextView time_travel;
        EditText edt_time_travel;
    }
}
