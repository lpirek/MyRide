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


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final RideCompare compare = items.get(position);

        return convertView;
    }

    class ViewHolder {
    }
}
