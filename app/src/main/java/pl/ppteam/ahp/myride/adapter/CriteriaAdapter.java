package pl.ppteam.ahp.myride.adapter;

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
import pl.ppteam.ahp.myride.common.Criterium;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Piotr Płaczek on 2015-05-27.
 */
public class CriteriaAdapter extends BaseAdapter{

    private final List<Criterium> items;
    private final Context context;

    private ViewHolder holder;

    public CriteriaAdapter(Context context, List<Criterium> items) {
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

    public List<Criterium> getSelectCriteriums() {
        List<Criterium> result = new ArrayList<Criterium>();

        for(Criterium criterium : items) {
            if (criterium.isSelected())
                result.add(criterium);
        }

        return result;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

            convertView = View.inflate(context, R.layout.adapter_criteria_item, null);
            holder = new ViewHolder();

            holder.wholeCriterium = (FrameLayout) convertView.findViewById(R.id.item);
            holder.criteriumSelected = (CheckBox) convertView.findViewById(R.id.item_chkbx);
            holder.criteriumName = (TextView) convertView.findViewById(R.id.item_name);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Criterium criterium = items.get(position);

        holder.criteriumName.setText(criterium.getName());

        holder.wholeCriterium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                criterium.setSelected(!criterium.isSelected());
                CriteriaAdapter.this.notifyDataSetChanged();
            }
        });

        holder.criteriumSelected.setChecked(criterium.isSelected());

        /*
        holder.criteriumSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                criterium.setSelected(isChecked);
            }
        });
        */

        return convertView;
    }

    class ViewHolder {
        FrameLayout wholeCriterium;
        CheckBox criteriumSelected;
        TextView criteriumName;
    }

}
