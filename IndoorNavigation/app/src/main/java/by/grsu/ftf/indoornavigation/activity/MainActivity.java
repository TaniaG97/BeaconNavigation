package by.grsu.ftf.indoornavigation.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import by.grsu.ftf.beaconlibrary.beacon.BeaconService;
import by.grsu.ftf.indoornavigation.R;

import static by.grsu.ftf.beaconlibrary.beacon.BeaconService.KEY_ID;
import static by.grsu.ftf.beaconlibrary.beacon.BeaconService.KEY_RSSI;


public class MainActivity extends AppCompatActivity implements OnClickListener {

    private TextView text;
    private BroadcastReceiver br;
    SharedPreferences sPref;

    final String SAVED_TEXT = "saved_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBroadcast();

        text = (TextView) findViewById(R.id.textview);
        text.setMovementMethod(new ScrollingMovementMethod());

        Button start = (Button) findViewById(R.id.start);
        start.setOnClickListener(this);
        Button stop = (Button) findViewById(R.id.stop);
        stop.setOnClickListener(this);

        loadText();

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                startservice();
                break;
            case R.id.stop:
                stopservice();
                break;
            default:
                break;
        }
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
                text.append("ID: " + id +"\n");
                String rssi = intent.getStringExtra(KEY_RSSI);
                text.append("RSSI: " + rssi +"\n");

                // auto scroll for text view
                final int scrollAmount = text.getLayout().getLineTop(text.getLineCount()) - text.getHeight();
                // if there is no need to scroll, scrollAmount will be <=0
                if (scrollAmount > 0)
                    text.scrollTo(0, scrollAmount);
            }
        };
    }

    void saveText() {
        sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT, text.getText().toString());
        ed.apply();
//        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
    }

    void loadText() {
        sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        text.setText(savedText);
//        Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }
}
