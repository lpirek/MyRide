package pl.ppteam.ahp.myride.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import pl.ppteam.ahp.myride.R;
import pl.ppteam.ahp.myride.common.Ride;
import pl.ppteam.ahp.myride.query.RideQuery;

/**
 * Created by Łukasz on 2015-06-02.
 */
public class LastSearchAdapter  extends BaseAdapter {

    private final List<RideQuery> items;
    private final Context context;

    private ViewHolder holder;

    public LastSearchAdapter(Context context, List<RideQuery> items) {
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

            convertView = View.inflate(context, R.layout.adapter_last_search_item, null);
            holder = new ViewHolder();

            holder.rideFrom = (TextView) convertView.findViewById(R.id.item_from);
            holder.rideTo = (TextView) convertView.findViewById(R.id.item_to);
            holder.rideDate = (TextView) convertView.findViewById(R.id.item_date);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final RideQuery query = items.get(position);

        holder.rideFrom.setText(query.getFromCity().getName());
        holder.rideTo.setText(query.getToCity().getName());
        holder.rideDate.setText("(" + new SimpleDateFormat("dd-MM-yyyy").format(query.getStartDate()) + ")");

        return convertView;
    }

    class ViewHolder {
        TextView rideFrom;
        TextView rideTo;
        TextView rideDate;
    }
}

