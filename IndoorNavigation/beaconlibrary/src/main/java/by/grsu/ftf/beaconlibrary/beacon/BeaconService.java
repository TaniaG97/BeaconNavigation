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

    private Handler mHandler = new Handler();


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        timeUpdaterRunnable.run();

        return START_NOT_STICKY;
    }


    private Runnable timeUpdaterRunnable = new Runnable() {
        public void run() {

//            BLEScan.startScan();

            Intent intent = new Intent("KEY_INTENT_FILTER");
            intent.putExtra(KEY_ID, beacon.GetID() );
            intent.putExtra(KEY_RSSI, beacon.GetRSSI() );
//            intent.putExtra(KEY_ID, BLEScan.getID());
//            intent.putExtra(KEY_RSSI, BLEScan.getRSSI() );
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            sendBroadcast(intent);

            mHandler.postDelayed(this, 100);
        }
    };


    @Override
    public void onDestroy() {
        mHandler.removeMessages(0);
        super.onDestroy();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
