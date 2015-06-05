package pl.ppteam.ahp.myride.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import pl.ppteam.ahp.myride.R;
import pl.ppteam.ahp.myride.common.MeansOfTransport;
import pl.ppteam.ahp.myride.query.RideQuery;

/**
 * Created by Łukasz on 2015-06-05.
 */
public class MeansOfTransportAdapter extends BaseAdapter {

        private final List<MeansOfTransport> items;
        private final Context context;

        private ViewHolder holder;

        public  MeansOfTransportAdapter(Context context, List<MeansOfTransport> items) {
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

                convertView = android.view.View.inflate(context, R.layout.adapter_means_of_transport_item, null);
                holder = new ViewHolder();

                holder.image = (ImageView) convertView.findViewById(R.id.item_img);
                holder.description = (TextView) convertView.findViewById(R.id.item_description);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final MeansOfTransport type = items.get(position);

            holder.image.setImageResource(type.getImage());
            holder.description.setText(type.getDescription());

            return convertView;
        }

        class ViewHolder {
            ImageView image;
            TextView description;
        }
}
