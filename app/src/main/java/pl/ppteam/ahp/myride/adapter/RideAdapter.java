package pl.ppteam.ahp.myride.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import pl.ppteam.ahp.myride.R;
import pl.ppteam.ahp.myride.common.Ride;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by £ukasz on 2015-05-26.
 */
public class RideAdapter extends BaseAdapter{

    private final List<Ride> items;
    private final Context context;

    private ViewHolder holder;

    public RideAdapter(Context context, List<Ride> items) {
        this.items = items;
        this.context = context;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

            convertView = View.inflate(context, R.layout.adapter_ride_item, null);
            holder = new ViewHolder();

            holder.rideSelected = (CheckBox) convertView.findViewById(R.id.item_chkbx);
            holder.rideStart = (TextView) convertView.findViewById(R.id.item_start_date);
            holder.rideFrom = (TextView) convertView.findViewById(R.id.item_from);
            holder.rideTo = (TextView) convertView.findViewById(R.id.item_to);
            holder.ridePrice = (TextView) convertView.findViewById(R.id.item_price);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Ride ride = items.get(position);

        holder.rideFrom.setText(ride.getFromCity().getName());
        holder.rideTo.setText(ride.getToCity().getName());
        holder.ridePrice.setText(new DecimalFormat("0.00").format(ride.getPrice()) + " z³");

        holder.rideStart.setText(new SimpleDateFormat("yyyy-MM-dd").format(ride.getStartDate()));

        return convertView;
    }

    class ViewHolder {
        CheckBox rideSelected;
        TextView rideFrom;
        TextView rideTo;
        TextView ridePrice;
        TextView rideStart;
        ImageView rideIcon;
    }
}
