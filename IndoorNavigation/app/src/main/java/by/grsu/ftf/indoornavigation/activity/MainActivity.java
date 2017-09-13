package by.grsu.ftf.indoornavigation.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import by.grsu.ftf.beaconlibrary.beacon.BeaconService;
import by.grsu.ftf.indoornavigation.R;


public class MainActivity extends AppCompatActivity {

    static TextView text;
    private Button start;
    private Button stop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.textview);
        text.setMovementMethod(new ScrollingMovementMethod());
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);

    }
    public static void setBeaconTextView(String beaconTextView) {
        text.append(beaconTextView+"\n");
        // auto scroll for text view
        final int scrollAmount = text.getLayout().getLineTop(text.getLineCount()) - text.getHeight();
        // if there is no need to scroll, scrollAmount will be <=0
        if (scrollAmount > 0)
            text.scrollTo(0, scrollAmount);
    }

    public  void startservice(View v){
        text.setText("");
        startService(new Intent(MainActivity.this, BeaconService.class));
    }
    public  void stopservice(View v){
        stopService(new Intent(MainActivity.this, BeaconService.class));
    }
}
