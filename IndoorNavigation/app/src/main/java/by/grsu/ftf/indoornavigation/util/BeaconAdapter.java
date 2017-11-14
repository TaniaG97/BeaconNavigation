package by.grsu.ftf.indoornavigation.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import by.grsu.ftf.beaconlibrary.beacon.Beacon;
import by.grsu.ftf.indoornavigation.R;

/**
 * Created by Admin on 31.10.2017.
 */

public class BeaconAdapter extends BaseAdapter {

    private List<Beacon> listBeacon;
    private final LayoutInflater inflater;

    public BeaconAdapter(Context context, List<Beacon> beacon) {
        listBeacon=beacon;
//        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listBeacon.size();
    }

    @Override
    public Beacon getItem(int position) {
        return listBeacon.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.item, null);
            final BeaconAdapter.ViewHolder holder = new BeaconAdapter.ViewHolder();
            holder.textView1 = (TextView) convertView.findViewById(R.id.txtId);
            holder.textView2 = (TextView) convertView.findViewById(R.id.txtRssi);
            convertView.setTag(holder);
        }

        final BeaconAdapter.ViewHolder holder = (BeaconAdapter.ViewHolder) convertView.getTag();
        final Beacon beacon = getItem(position);
        holder.textView1.setText(beacon.getId());
        holder.textView2.setText(beacon.getRssi());
        return convertView;
    }

    private class ViewHolder {
        public TextView textView1;
        public TextView textView2;
    }
}
