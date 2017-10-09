package by.grsu.ftf.beaconlibrary.beacon;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import by.grsu.ftf.beaconlibrary.bluetooth.BLEScan;



public class BeaconService extends Service {
    public BeaconService() {
    }

    Beacon beacon = new Beacon();
    BLEScan BLEScan = new BLEScan();

    public static final String KEY_ID = "KEY_ID";
    public static final String KEY_RSSI = "KEY_RSSI";

    private Handler mHandler = new Handler();
    private final IBinder mBinder = new MyBinder();
    Callbacks callbacks;
    boolean serviceRun = true;


    private Runnable timeUpdaterRunnable = new Runnable() {
        public void run() {

            BLEScan.startScan();

            if(BLEScan.GetId().equals("ID")) {
                callbacks.GetBeaconFromService(beacon.GetId(), beacon.GetRssi());
            }
            else {
                callbacks.GetBeaconFromService(BLEScan.GetId(), BLEScan.GetRssi());
            }


            mHandler.postDelayed(this, 100);
        }
    };

    public void InitClient(Activity activity) {
        this.callbacks = (Callbacks) activity;
        if (serviceRun) {
            timeUpdaterRunnable.run();
            serviceRun = false;
        }
    }

    @Override
    public void onDestroy() {
        mHandler.removeCallbacks(timeUpdaterRunnable);
        BLEScan.stopScan();
        serviceRun = true;
        super.onDestroy();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class MyBinder extends Binder {
        public BeaconService getService() {
            return BeaconService.this;
        }
    }

    public interface Callbacks {
        void GetBeaconFromService(String id, String rssi);
    }
}
