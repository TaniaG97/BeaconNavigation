package by.grsu.ftf.beaconlibrary.beacon;


import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Admin on 16.08.2017.
 */

public class Beacon {

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


    public String GetRSSI(){
        int rssi= random.nextInt(30)-80;
        return String.valueOf(rssi);
    }

    public String GetID(){
        String id = listID.get(random.nextInt(listID.size()));
        return id;
    }

    private void CreateBeacon(String id, int rssi0, int x, int y){
        listID.add(id);
        listRSSI0.add(rssi0);
        listX.add(x);
        listY.add(y);
    }
}