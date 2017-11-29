package by.grsu.ftf.indoornavigation.util;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import by.grsu.ftf.beaconlibrary.beacon.Beacon;
import by.grsu.ftf.indoornavigation.R;

/**
 * Created by Admin on 24.11.2017.
 */

public class BeaconRecyclerViewAdapter extends RecyclerView.Adapter<BeaconRecyclerViewAdapter.ViewHolder> {

    private List<Beacon> beaconsList = new ArrayList<>();

    public void setBeaconList(List<Beacon> beacon) {
        this.beaconsList = beacon;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BeaconRecyclerViewAdapter.ViewHolder holder, int position) {
        Beacon beacon = beaconsList.get(position);
        float coefficient = Math.abs((-90.0F - beacon.getRssi())/( -35.0F - -90.0F));
        holder.textView1.setText("Name: "+beacon.getId());
        holder.textView2.setText("RSSI: "+String.valueOf(beacon.getRssi()));
        holder.rssiBar.setValue(coefficient);
    }

    @Override
    public int getItemCount() {
        return beaconsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView textView1;
        final TextView textView2;
        final RssiBar2 rssiBar;
        ViewHolder(View view){
            super(view);
            textView1 = (TextView) view.findViewById(R.id.txtId);
            textView2 = (TextView) view.findViewById(R.id.txtRssi);
            rssiBar = (RssiBar2) view.findViewById(R.id.RssiBar);
        }
    }
}
