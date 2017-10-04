package by.grsu.ftf.beaconlibrary.beacon;


import java.util.ArrayList;
import java.util.Random;

import by.grsu.ftf.beaconlibrary.BeaconInterface;

/**
 * Created by Admin on 16.08.2017.
 */

public class Beacon implements BeaconInterface{

    private ArrayList<String> listID = new ArrayList<>();
    private ArrayList<Integer> listRSSI0 = new ArrayList<>();
    private ArrayList<Integer> listX = new ArrayList<>();
    private ArrayList<Integer> listY = new ArrayList<>();

    Random random = new Random();


    public Beacon() {
        CreateBeacon("id1",65,1,1);
        CreateBeacon("id2",65,1,2);
        CreateBeacon("id3",65,2,2);
        CreateBeacon("id4",65,2,1);
    }

    private void CreateBeacon(String id, int rssi0, int x, int y){
        listID.add(id);
        listRSSI0.add(rssi0);
        listX.add(x);
        listY.add(y);
    }

    @Override
    public String GetRssi() {
        int rssi= random.nextInt(30)-80;
        return String.valueOf(rssi);
    }

    @Override
    public String GetId() {
        String id = listID.get(random.nextInt(listID.size()));
        return id;
    }
}