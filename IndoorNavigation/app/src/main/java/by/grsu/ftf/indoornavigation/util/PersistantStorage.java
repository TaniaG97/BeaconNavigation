package by.grsu.ftf.indoornavigation.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.grsu.ftf.beaconlibrary.beacon.Beacon;

/**
 * Created by Admin on 08.10.2017.
 */

public class PersistantStorage {
    public static final String STORAGE_NAME = "StorageName";

    private static SharedPreferences settings = null;
    private static SharedPreferences.Editor editor = null;
    private static Context context = null;


    private static void init(){
        settings = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
    }

    public static void addBeaconList(List<Beacon> list){
        if( settings == null ){
            init();
        }
        for(Beacon i : list) {
            editor.putString( i.getId(), String.valueOf(i.getRssi()));
            editor. apply();
        }

    }

    public static String getBeacon( String name ){
        if( settings == null ){
            init();
        }
        return settings.getString( name, null );
    }

    public static Map getBeaconsMap(){
        if( settings == null ){
            init();
        }
        return settings.getAll();
    }

}
