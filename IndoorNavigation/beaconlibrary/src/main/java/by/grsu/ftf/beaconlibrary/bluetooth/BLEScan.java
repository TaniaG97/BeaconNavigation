package by.grsu.ftf.beaconlibrary.bluetooth;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.os.Build;
import android.util.Log;


/**
 * Created by Admin on 16.08.2017.
 */



public class BLEScan extends Activity{


    private BluetoothAdapter bluetoothAdapter;
    private BluetoothLeScanner bluetoothLeScanner;
    private int rssi ;
    private String id ;


    public BLEScan() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        bluetoothAdapter.enable();
    }

    public String getRSSI() {
        return String.valueOf(rssi);
    }

    public String getID() {
        return id;
    }

    private void setRssi(int rssi) {
        this.rssi = rssi;
    }

    private void setId(String id) {
        this.id = id;
    }

    public void startScan() {
//        if (!bluetoothAdapter.isEnabled()) {
//            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//            startActivityForResult(turnOn, 0);
//            Toast.makeText(getApplicationContext(), "Turned on",Toast.LENGTH_LONG).show();
//        }


        if(Build.VERSION.SDK_INT < 21){
            bluetoothAdapter.startLeScan(LeScanCallback);
        }else {
            bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();
            ScanSettings settings = new ScanSettings.Builder()
                    .setReportDelay(0)
                    .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
                    .build();
            bluetoothLeScanner.startScan(null, settings, scanCallback);
        }

    }

    public void stopScan() {
        if(Build.VERSION.SDK_INT < 21) {
            bluetoothAdapter.stopLeScan(LeScanCallback);
        }else{
            bluetoothLeScanner.stopScan(scanCallback);
            bluetoothLeScanner = null;
        }
    }

    //SDK>21
    @SuppressLint("NewApi")
    private ScanCallback scanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            BluetoothDevice device = result.getDevice();
            setId(device.getName());
            setRssi(result.getRssi());
            Log.d("Log", device.getName() +"  "+ result.getRssi());

        }
    };

    //SDK<21
    private BluetoothAdapter.LeScanCallback LeScanCallback = new BluetoothAdapter.LeScanCallback() {
        public void onLeScan(final BluetoothDevice device, final int rssi, final byte[] scanRecord) {
            setId(device.getName());
            setRssi(rssi);
            Log.d("Log", device.getName() +"  " + rssi);
        }
    };


}
