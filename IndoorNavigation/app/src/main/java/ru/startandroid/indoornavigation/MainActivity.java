package ru.startandroid.indoornavigation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import ru.startandroid.beaconlibrary.Filter;
import ru.startandroid.beaconlibrary.InitBeacon;
import ru.startandroid.beaconlibrary.MathOperations;
import ru.startandroid.indoornavigation.sensors.SensorsChange;

public class MainActivity extends AppCompatActivity {

    TextView text;
    Map map=new Map();
    SensorsChange sensor=new SensorsChange();
    Filter filter=new Filter();
    InitBeacon beacon=new InitBeacon();
    MathOperations math=new MathOperations();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.textview);

        text.setText(text.getText() + map.maptxt  + "\n");
        text.setText(text.getText() + sensor.sensortxt  + "\n");
        text.setText(text.getText() + filter.filtertxt  + "\n");
        text.setText(text.getText() + beacon.beacontxt  + "\n");
        text.setText(text.getText() + math.mathtxt  + "\n");

    }
}
