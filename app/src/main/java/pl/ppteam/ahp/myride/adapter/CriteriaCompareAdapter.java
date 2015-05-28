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

/**
 * Created by Łukasz on 2015-05-28.
 */
public class CriteriaCompareAdapter extends BaseAdapter {

    private final List<CriteriaCompare> items;
    private final Context context;

    private ViewHolder holder;

    public CriteriaCompareAdapter(Context context, List<CriteriaCompare> items) {
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
            convertView = View.inflate(context, R.layout.adapter_criteria_compare_item, null);
            holder = new ViewHolder();

            holder.criterium1 = (TextView) convertView.findViewById(R.id.item_criterium1);
            holder.criterium2 = (TextView) convertView.findViewById(R.id.item_criterium2);
            holder.imgWage = (ImageView) convertView.findViewById(R.id.item_img_wage);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final CriteriaCompare compare = items.get(position);

        holder.criterium1.setText(compare.getCriterium1().getName());
        holder.criterium2.setText(compare.getCriterium2().getName());

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
        TextView criterium1;
        TextView criterium2;
        ImageView imgWage;
    }

}