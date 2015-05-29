package pl.ppteam.ahp.myride.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import pl.ppteam.ahp.myride.R;
import pl.ppteam.ahp.myride.common.CriteriaCompare;
import pl.ppteam.ahp.myride.common.Direction;
import pl.ppteam.ahp.myride.common.RideCompare;

/**
 * Created by Łukasz on 2015-05-28.
 */
public class RideCompareAdapter extends BaseAdapter {

    private final List<RideCompare> items;
    private final Context context;

    private ViewHolder holder;

    public RideCompareAdapter(Context context, List<RideCompare> items) {
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
            convertView = View.inflate(context, R.layout.adapter_ride_compare_item, null);
            holder = new ViewHolder();

            holder.ride1 = (TextView) convertView.findViewById(R.id.item_ride1);
            holder.ride2 = (TextView) convertView.findViewById(R.id.item_ride2);
            holder.imgWage = (ImageView) convertView.findViewById(R.id.item_ride_img_wage);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final RideCompare compare = items.get(position);

        holder.ride1.setText(compare.getRide1().getTransportType().toString());
        holder.ride2.setText(compare.getRide2().getTransportType().toString());

        int resourceId;

        switch(compare.getWage()) {
            case W3:
                resourceId = compare.getDirection() == Direction.Rightside ?
                        R.drawable.three_right : R.drawable.three_left;
                break;
            case W5:
                resourceId = compare.getDirection() == Direction.Rightside ?
                        R.drawable.five_right : R.drawable.five_left;
                break;
            case W7:
                resourceId = compare.getDirection() == Direction.Rightside ?
                        R.drawable.seven_right : R.drawable.seven_left;
                break;
            case W9:
                resourceId = compare.getDirection() == Direction.Rightside ?
                        R.drawable.nine_right : R.drawable.nine_left;
                break;
            default:
                resourceId = R.drawable.equal;
                break;
        }

        holder.imgWage.setImageResource(resourceId);

        return convertView;
    }

    class ViewHolder {
        TextView ride1;
        TextView ride2;
        ImageView imgWage;
    }
}
