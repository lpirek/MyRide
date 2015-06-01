package pl.ppteam.ahp.myride.adapter;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Node;
import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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

            holder.imgWage = (ImageView) convertView.findViewById(R.id.item_ride_img_wage);
            holder.img_ride1 = (ImageView) convertView.findViewById(R.id.img_ride1);
            holder.img_ride2 = (ImageView) convertView.findViewById(R.id.img_ride2);
            holder.from_ride1 = (TextView) convertView.findViewById(R.id.item_ride1_from);
            holder.to_ride1 = (TextView) convertView.findViewById(R.id.item_ride1_to);
            holder.from_ride2 = (TextView) convertView.findViewById(R.id.item_ride2_from);
            holder.to_ride2 = (TextView) convertView.findViewById(R.id.item_ride2_to);
            holder.price_ride1 = (TextView) convertView.findViewById(R.id.item_price_ride1);
            holder.price_ride2 = (TextView) convertView.findViewById(R.id.item_price_ride2);
            holder.time_ride1 = (TextView) convertView.findViewById(R.id.item_time_ride1);
            holder.time_ride2 = (TextView) convertView.findViewById(R.id.item_time_ride2);
            holder.startDate_ride1 = (TextView) convertView.findViewById(R.id.item_startDate_ride1);
            holder.startDate_ride2 = (TextView) convertView.findViewById(R.id.item_startDate_ride2);
            holder.endDate_ride1 = (TextView) convertView.findViewById(R.id.item_endDate_ride1);
            holder.endDate_ride2 = (TextView) convertView.findViewById(R.id.item_endDate_ride2);
            holder.item_line_ride1 = (TextView) convertView.findViewById(R.id.item_line_ride1);
            holder.item_line_ride2 = (TextView) convertView.findViewById(R.id.item_line_ride2);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final RideCompare compare = items.get(position);

        holder.from_ride1.setText(compare.getRide1().getFromCity().getName());
        holder.to_ride1.setText(compare.getRide1().getToCity().getName());

        holder.from_ride2.setText(compare.getRide2().getFromCity().getName());
        holder.to_ride2.setText(compare.getRide2().getToCity().getName());

        holder.price_ride1.setText(new DecimalFormat("0.00").format(compare.getRide1().getPrice()) + " zł");
        holder.price_ride2.setText(new DecimalFormat("0.00").format(compare.getRide2().getPrice()) + " zł");

        holder.time_ride1.setText(compare.getRide1().getFormatRideTime());
        holder.time_ride2.setText(compare.getRide2().getFormatRideTime());

        holder.startDate_ride1.setText(new SimpleDateFormat("yyyy-MM-dd").format(compare.getRide1().getStartDate()));
        holder.startDate_ride2.setText(new SimpleDateFormat("yyyy-MM-dd").format(compare.getRide2().getStartDate()));

        if (compare.getRide1().getEndDate() == null)
        {
            holder.endDate_ride1.setText("");
            holder.endDate_ride1.setVisibility(View.GONE);
            holder.item_line_ride1.setVisibility(View.GONE);
        }else {
            holder.endDate_ride1.setText(new SimpleDateFormat("yyyy-MM-dd").format(compare.getRide1().getEndDate()));
        }

        if(compare.getRide2().getEndDate() == null)
        {
            holder.endDate_ride2.setText("");
            holder.endDate_ride2.setVisibility(View.GONE);
            holder.item_line_ride2.setVisibility(View.GONE);
        }
        else {
            holder.endDate_ride2.setText(new SimpleDateFormat("yyyy-MM-dd").format(compare.getRide2().getEndDate()));
        }

        holder.img_ride1.setImageResource(compare.getRide1().getTransportType().getImage());
        holder.img_ride2.setImageResource(compare.getRide2().getTransportType().getImage());

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

        ImageView imgWage;

        ImageView img_ride1;
        ImageView img_ride2;

        TextView from_ride1;
        TextView to_ride1;

        TextView from_ride2;
        TextView to_ride2;

        TextView price_ride1;
        TextView price_ride2;

        TextView time_ride1;
        TextView time_ride2;

        TextView startDate_ride1;
        TextView startDate_ride2;

        TextView endDate_ride1;
        TextView endDate_ride2;

        TextView item_line_ride1;
        TextView item_line_ride2;
    }
}
