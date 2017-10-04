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

import by.grsu.ftf.beaconlibrary.BeaconInterface;


/**
 * Created by Admin on 16.08.2017.
 */



public class BLEScan extends Activity implements BeaconInterface{

    private BluetoothAdapter bluetoothAdapter;
    private BluetoothLeScanner bluetoothLeScanner;
    private String mId = "ID";
    private String mRssi = "RSSI";

    public BLEScan() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    }

    public void startScan() {
        if (!bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.enable();
        }

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
            Log.d("Log", device.getName() +"  "+ result.getRssi());

            mId = device.getName();
            mRssi = String.valueOf(result.getRssi());
        }
    };

    //SDK<21
    private BluetoothAdapter.LeScanCallback LeScanCallback = new BluetoothAdapter.LeScanCallback() {
        public void onLeScan(final BluetoothDevice device, final int rssi, final byte[] scanRecord) {
            Log.d("Log", device.getName() +"  " + rssi);
            mId = device.getName();
            mRssi = String.valueOf(rssi);
        }
    };


    @Override
    public String GetRssi() {
        return  mRssi;
    }

    @Override
    public String GetId() {
        return mId;
    }
}
