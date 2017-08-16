package by.grsu.ftf.indoornavigation.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import by.grsu.ftf.beaconlibrary.beacon.Beacon;
import by.grsu.ftf.beaconlibrary.beacon.Filter;
import by.grsu.ftf.beaconlibrary.beacon.MathOperation;
import by.grsu.ftf.beaconlibrary.bluetooth.Bluetooth;
import by.grsu.ftf.indoornavigation.R;
import by.grsu.ftf.indoornavigation.map.Map;
import by.grsu.ftf.indoornavigation.map.PathCalculator;

public class MainActivity extends AppCompatActivity {

    TextView text;

    Map map=new Map();
    PathCalculator path = new PathCalculator();
    Bluetooth blutooth = new Bluetooth();
    Filter filter=new Filter();
    Beacon beacon=new Beacon();
    MathOperation math=new MathOperation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.textview);

        text.setText(text.getText() + map.getMapTxt()  + "\n");
        text.setText(text.getText() + path.getPathTxt()  + "\n");
        text.setText(text.getText() + filter.getFilterTxt()  + "\n");
        text.setText(text.getText() + beacon.getBeaconTxt()  + "\n");
        text.setText(text.getText() + math.getMathTxt()  + "\n");
        text.setText(text.getText() + blutooth.getBluetoothTxt()  + "\n");

    }
}
