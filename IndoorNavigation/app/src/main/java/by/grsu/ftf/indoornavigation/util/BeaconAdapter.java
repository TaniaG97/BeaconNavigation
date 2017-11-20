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

    public BeaconAdapter(Context context, List<Beacon> beacon) {
        listBeacon=beacon;
    }

    public void setBeaconList(List<Beacon> beacon) {
        this.listBeacon = beacon;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
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
        View view = convertView;
        BeaconHolder beaconHolder;
        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            TextView textViewIdBeacon = (TextView) view.findViewById(R.id.txtId);
            TextView textViewRSSIBeacon = (TextView) view.findViewById(R.id.txtRssi);
            RssiBar rssiBarCoefficient = (RssiBar) view.findViewById(R.id.RssiBar);
            beaconHolder = new BeaconHolder(textViewIdBeacon, textViewRSSIBeacon, rssiBarCoefficient);
            view.setTag(beaconHolder);
        } else {
            beaconHolder = (BeaconHolder) view.getTag();
        }
        Beacon beacon = getItem(position);
        float coefficient = Math.abs((-90.0F - beacon.getRssi())/( -35.0F - -90.0F));
        beaconHolder.textViewIdBeacon.setText(beacon.getId());
        beaconHolder.textViewRSSIBeacon.setText(String.valueOf(beacon.getRssi()));
        beaconHolder.rssiBarCoefficient.setValue(coefficient);
        return view;
    }

    private class BeaconHolder {
        private TextView textViewIdBeacon;
        private TextView textViewRSSIBeacon;
        private RssiBar rssiBarCoefficient;

        private BeaconHolder(TextView textViewIdBeacon, TextView textViewRSSIBeacon, RssiBar rssiBarCoefficient) {
            this.textViewIdBeacon = textViewIdBeacon;
            this.textViewRSSIBeacon = textViewRSSIBeacon;
            this.rssiBarCoefficient = rssiBarCoefficient;
        }
    }
}
