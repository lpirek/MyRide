package pl.ppteam.ahp.myride.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import pl.ppteam.ahp.myride.R;
import pl.ppteam.ahp.myride.common.Ride;

/**
 * Created by Piotr Płaczek on 2015-05-28.
 */
public class RankingAdapter extends BaseAdapter {
    private final List<Ride> items;
    private final Context context;

    private ViewHolder holder;

    public RankingAdapter(Context context, List<Ride> items) {
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

            convertView = View.inflate(context, R.layout.adapter_ranking_item, null);
            holder = new ViewHolder();

            holder.positionRanking = (TextView) convertView.findViewById(R.id.item_position_ranking);
            holder.rideStart = (TextView) convertView.findViewById(R.id.item_start_date);
            holder.rideFrom = (TextView) convertView.findViewById(R.id.item_from);
            holder.rideTo = (TextView) convertView.findViewById(R.id.item_to);
            holder.ridePrice = (TextView) convertView.findViewById(R.id.item_price);
            holder.rideIcon = (ImageView) convertView.findViewById(R.id.item_img);
            holder.disparityView = (View) convertView.findViewById(R.id.item_disparity);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Ride ride = items.get(position);

        holder.positionRanking.setText(ride.getRankingPosition()+".");
        holder.rideFrom.setText(ride.getFromCity().getName());
        holder.rideTo.setText(ride.getToCity().getName());
        holder.ridePrice.setText(new DecimalFormat("0.00").format(ride.getPrice()) + " zł");
        holder.rideStart.setText(new SimpleDateFormat("yyyy-MM-dd").format(ride.getStartDate()));
        holder.rideIcon.setImageResource(ride.getTransportType().getImage());

        setColor(position);

        return convertView;
    }

    private boolean setColor(int position) {

        Ride currentRide = items.get(position);
        boolean isSet = false;

        if (position + 1 < getCount()) {
            Ride nextRide = items.get(position + 1);

            if (currentRide.getRankingValue() == nextRide.getRankingValue()) {
                holder.disparityView.setBackgroundColor(Color.rgb(0, 255, 0));
                isSet = true;
            }
            else if (Math.abs(currentRide.getRankingValue() - nextRide.getRankingPosition()) > 0.1) {
                holder.disparityView.setBackgroundColor(Color.rgb(255, 0, 0));
                isSet = true;
            }
            else {
                holder.disparityView.setBackgroundColor(Color.rgb(217, 200, 14));
                isSet = true;
            }
        }

        return isSet;
    }

    class ViewHolder {
        TextView positionRanking;
        TextView rideFrom;
        TextView rideTo;
        TextView ridePrice;
        TextView rideStart;
        ImageView rideIcon;
        View disparityView;
    }
}
