package by.grsu.ftf.indoornavigation.util;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import by.grsu.ftf.beaconlibrary.beacon.Beacon;
import by.grsu.ftf.indoornavigation.R;

/**
 * Created by Admin on 31.10.2017.
 */

public class BeaconAdapter2 extends BaseAdapter {

    private List<Beacon> listBeacon;
    private final LayoutInflater inflater;

    public BeaconAdapter2(Context context, List<Beacon> beacon) {
        listBeacon=beacon;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setBeaconList(List<Beacon> beacon) {
        this.listBeacon = beacon;
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
            convertView = inflater.inflate(R.layout.item2, parent, false);
            final BeaconAdapter2.ViewHolder holder = new BeaconAdapter2.ViewHolder();
            holder.textView1 = (TextView) convertView.findViewById(R.id.txtId);
            holder.textView2 = (TextView) convertView.findViewById(R.id.txtRssi);
            holder.rssiBar = (RssiBar2) convertView.findViewById(R.id.RssiBar);
            convertView.setTag(holder);
        }

        final BeaconAdapter2.ViewHolder holder = (BeaconAdapter2.ViewHolder) convertView.getTag();
        final Beacon beacon = getItem(position);
        float coefficient = Math.abs((-90.0F - beacon.getRssi())/( -35.0F - -90.0F));
        holder.textView1.setText("Name: "+beacon.getId());
        holder.textView2.setText("RSSI: "+String.valueOf(beacon.getRssi()));
        holder.rssiBar.setValue(coefficient);
        return convertView;
    }

    private class ViewHolder {
        public TextView textView1;
        public TextView textView2;
        public RssiBar2 rssiBar;
    }
}
