package pl.ppteam.ahp.myride.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import pl.ppteam.ahp.myride.common.Ride;
import java.util.List;

/**
 * Created by £ukasz on 2015-05-26.
 */
public class RideAdapter extends BaseAdapter{

    private final List<Ride> items;
    private final Context context;

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
        //Zrobiæ

        return null;
    }
}
