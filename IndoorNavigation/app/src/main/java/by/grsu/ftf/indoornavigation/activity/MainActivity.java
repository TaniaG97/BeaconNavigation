package by.grsu.ftf.indoornavigation.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import by.grsu.ftf.beaconlibrary.beacon.BeaconService;
import by.grsu.ftf.indoornavigation.R;

import static by.grsu.ftf.beaconlibrary.beacon.BeaconService.KEY_ID;
import static by.grsu.ftf.beaconlibrary.beacon.BeaconService.KEY_RSSI;


public class MainActivity extends AppCompatActivity {

    private TextView text;
    private BroadcastReceiver br;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBroadcast();

        text = (TextView) findViewById(R.id.textview);
        text.setMovementMethod(new ScrollingMovementMethod());

        Button start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startservice();
            }
        });
        Button stop = (Button) findViewById(R.id.stop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopservice();
            }
        });
    }

    private void startservice(){
        text.setText("");
        startService(new Intent(MainActivity.this, BeaconService.class));
    }
    private void stopservice(){
        stopService(new Intent(MainActivity.this, BeaconService.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(br, new IntentFilter("KEY_INTENT_FILTER"));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(br);
    }

    private void initBroadcast() {
        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String id = intent.getStringExtra(KEY_ID);
                setBeaconTextView("ID: " + id);
                String rssi = intent.getStringExtra(KEY_RSSI);
                setBeaconTextView("RSSI: " + rssi);
            }
        };
    }
    public void setBeaconTextView(String beaconTextView) {
        text.append(beaconTextView+"\n");
        // auto scroll for text view
        final int scrollAmount = text.getLayout().getLineTop(text.getLineCount()) - text.getHeight();
        // if there is no need to scroll, scrollAmount will be <=0
        if (scrollAmount > 0)
            text.scrollTo(0, scrollAmount);
    }
}
