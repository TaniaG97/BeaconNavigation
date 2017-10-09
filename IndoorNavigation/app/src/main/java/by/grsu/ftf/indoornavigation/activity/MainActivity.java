package by.grsu.ftf.indoornavigation.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import by.grsu.ftf.beaconlibrary.beacon.BeaconService;
import by.grsu.ftf.indoornavigation.R;


public class MainActivity extends AppCompatActivity implements BeaconService.Callbacks {

    private ListView lvSimple;

    boolean mBound;
    private BeaconService myBinder;

    // имена атрибутов для Map
    final String ATTRIBUTE_NAME_TEXTID = "textId";
    final String ATTRIBUTE_NAME_TEXTRSSI = "textRssi";
    private Map<String, Object> map = new HashMap<String, Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    public void GetBeaconFromService(String id, String rssi) {

        ArrayList<Map<String, Object>> data = SortingBeacon(id,rssi);

        // массив имен атрибутов, из которых будут читаться данные
        String[] from = { ATTRIBUTE_NAME_TEXTID, ATTRIBUTE_NAME_TEXTRSSI};
        // массив ID View-компонентов, в которые будут вставлять данные
        int[] to = { R.id.txtId, R.id.txtRssi };

        // создаем адаптер
        SimpleAdapter sAdapter = new SimpleAdapter(this, data, R.layout.item,
                from, to);

        // определяем список и присваиваем ему адаптер
        lvSimple = (ListView) findViewById(R.id.lvSimple);
        lvSimple.setAdapter(sAdapter);
    }

    private ArrayList<Map<String, Object>> SortingBeacon(String id, String rssi){

        map.put(id, rssi);

        ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> m;
        for (Map.Entry entry : map.entrySet()) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_TEXTID, entry.getKey());
            m.put(ATTRIBUTE_NAME_TEXTRSSI, entry.getValue());
            list.add(m);
        }

        return list;
    }
}
