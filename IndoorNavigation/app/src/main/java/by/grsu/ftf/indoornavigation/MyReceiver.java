package by.grsu.ftf.indoornavigation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import by.grsu.ftf.indoornavigation.activity.MainActivity;

import static by.grsu.ftf.beaconlibrary.beacon.BeaconService.KEY_ID;
import static by.grsu.ftf.beaconlibrary.beacon.BeaconService.KEY_RSSI;

public class MyReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        String id = intent.getStringExtra(KEY_ID);
        Log.d("Log", id);
        MainActivity.setBeaconTextView("ID: " + id);
        String rssi = intent.getStringExtra(KEY_RSSI);
        Log.d("Log", rssi);
        MainActivity.setBeaconTextView("RSSI: " + rssi);

    }
}