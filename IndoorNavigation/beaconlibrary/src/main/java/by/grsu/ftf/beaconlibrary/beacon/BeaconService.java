package by.grsu.ftf.beaconlibrary.beacon;

import android.app.Service;
import android.content.Intent;
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
//    public static final String KEY_ID_Beacon = "KEY_ID_Beacon";
//    public static final String KEY_RSSI_Beacon = "KEY_RSSI_Beacon";
//    public static final String KEY_ID_BLEScan = "KEY_ID_BLEScan";
//    public static final String KEY_RSSI_BLEScan = "KEY_RSSI_BLEScan";

    private Handler mHandler = new Handler();


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        timeUpdaterRunnable.run();

        return START_NOT_STICKY;
    }


    private Runnable timeUpdaterRunnable = new Runnable() {
        public void run() {

            BLEScan.startScan();

            Intent intent = new Intent("KEY_INTENT_FILTER");

            if(BLEScan.GetId().equals("ID")) {
                intent.putExtra(KEY_ID, beacon.GetId());
                intent.putExtra(KEY_RSSI, beacon.GetRssi());
            }
            else {
                intent.putExtra(KEY_ID, BLEScan.GetId());
                intent.putExtra(KEY_RSSI, BLEScan.GetRssi());
            }
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            sendBroadcast(intent);

            mHandler.postDelayed(this, 100);
        }
    };


    @Override
    public void onDestroy() {
        mHandler.removeMessages(0);
        BLEScan.stopScan();
        super.onDestroy();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
