package by.grsu.ftf.indoornavigation.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import by.grsu.ftf.beaconlibrary.beacon.Beacon;
import by.grsu.ftf.beaconlibrary.beacon.BeaconService;
import by.grsu.ftf.indoornavigation.R;
import by.grsu.ftf.indoornavigation.util.BeaconAdapter;
import by.grsu.ftf.indoornavigation.util.BeaconAdapter2;


public class MainActivity extends AppCompatActivity implements BeaconService.Callbacks {

    private ListView lvSimple;

    boolean mBound;
    private BeaconService myBinder;
    private BeaconAdapter bAdapter;

    private HashMap<String, Beacon> beaconHashMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvSimple = (ListView) findViewById(R.id.lvSimple);

        List<Beacon> data = new ArrayList<>();
        bAdapter = new BeaconAdapter(this, data);
        lvSimple.setAdapter(bAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService(new Intent(MainActivity.this, BeaconService.class), mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BeaconService.MyBinder binder = (BeaconService.MyBinder) service;
            myBinder = binder.getService();
            myBinder.InitClient(MainActivity.this);
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void GetBeaconFromService(Beacon beacon) {

        List<Beacon> data = SortingBeacon(beacon);
        bAdapter.setBeaconList(data);
        bAdapter.notifyDataSetChanged();
    }

    private List<Beacon> SortingBeacon(Beacon beacon){
        List<Beacon> list = new ArrayList<>();
        beaconHashMap.put(beacon.getId(),beacon);
        for (Beacon s : beaconHashMap.values()) {
            list.add(s);
        }

        return list;
    }
}
