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

public class BeaconAdapter2 extends BaseAdapter {

    private List<Beacon> listBeacon;
    private LayoutInflater inflater;

    public BeaconAdapter2(Context context, List<Beacon> beacon) {
        this.listBeacon=beacon;
        this.inflater = LayoutInflater.from(context);
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
        ViewHolder holder;
        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.item2, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        final Beacon beacon = getItem(position);
        float coefficient = Math.abs((-90.0F - beacon.getRssi())/( -35.0F - -90.0F));
        holder.textView1.setText("Name: "+beacon.getId());
        holder.textView2.setText("RSSI: "+String.valueOf(beacon.getRssi()));
        holder.rssiBar.setValue(coefficient);
        return convertView;
    }


    private class ViewHolder {
        final TextView textView1;
        final TextView textView2;
        final RssiBar2 rssiBar;
        ViewHolder(View view){
            textView1 = (TextView) view.findViewById(R.id.txtId);
            textView2 = (TextView) view.findViewById(R.id.txtRssi);
            rssiBar = (RssiBar2) view.findViewById(R.id.RssiBar);
        }
    }
}
