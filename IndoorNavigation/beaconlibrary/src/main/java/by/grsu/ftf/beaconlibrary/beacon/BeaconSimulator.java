package by.grsu.ftf.beaconlibrary.beacon;


import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Admin on 16.08.2017.
 */

public class BeaconSimulator {

    private ArrayList<String> listID = new ArrayList<>();
    private ArrayList<Integer> listRSSI0 = new ArrayList<>();
    private ArrayList<Integer> listX = new ArrayList<>();
    private ArrayList<Integer> listY = new ArrayList<>();

    private Random random = new Random();


    public BeaconSimulator() {
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

    public Beacon GetBeacon() {
        String id = listID.get(random.nextInt(listID.size()));
        int rssi= random.nextInt(30)-80;
        Beacon beacon = new Beacon(id,rssi);
        return beacon;
    }
}