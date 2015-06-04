package pl.ppteam.ahp.myride.adapter;

import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import pl.ppteam.ahp.myride.R;
import pl.ppteam.ahp.myride.common.Ride;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Łukasz on 2015-05-26.
 */
public class RideAdapter extends BaseAdapter{

    private List<Ride> items;
    private Context context;

    private ViewHolder holder;

    public RideAdapter(Context context, List<Ride> items) {
        this.items = items;
        this.context = context;
    }

    public List<Ride> getItems() {
        return items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public List<Ride> getSelectedRides() {
        List<Ride> result = new ArrayList<Ride>();

        for(Ride ride : items) {
            if (ride.isSelected())
                result.add(ride);
        }

        return result;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

            convertView = View.inflate(context, R.layout.adapter_ride_item, null);
            holder = new ViewHolder();

            holder.wholeRide = (FrameLayout) convertView.findViewById(R.id.item);
            holder.rideSelected = (CheckBox) convertView.findViewById(R.id.item_chkbx);
            holder.rideStart = (TextView) convertView.findViewById(R.id.item_start_date);
            holder.rideFrom = (TextView) convertView.findViewById(R.id.item_from);
            holder.rideTo = (TextView) convertView.findViewById(R.id.item_to);
            holder.ridePrice = (TextView) convertView.findViewById(R.id.item_price);
            holder.rideIcon = (ImageView) convertView.findViewById(R.id.item_img);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Ride ride = items.get(position);

        holder.rideFrom.setText(ride.getFromCity().getName());
        holder.rideTo.setText(ride.getToCity().getName());
        holder.ridePrice.setText(new DecimalFormat("0.00").format(ride.getPrice()) + " zł");
        holder.rideStart.setText(ride.getFormatStartDate());
        holder.rideIcon.setImageResource(ride.getTransportType().getImage());

        holder.wholeRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ride.setSelected(!ride.isSelected());
                RideAdapter.this.notifyDataSetChanged();
            }
        });

        holder.rideSelected.setChecked(ride.isSelected());

        /*
        holder.rideSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ride.setSelected(isChecked);
            }
        });
        */

        return convertView;
    }

    class ViewHolder {
        FrameLayout wholeRide;
        CheckBox rideSelected;
        TextView rideFrom;
        TextView rideTo;
        TextView ridePrice;
        TextView rideStart;
        ImageView rideIcon;
    }
}
