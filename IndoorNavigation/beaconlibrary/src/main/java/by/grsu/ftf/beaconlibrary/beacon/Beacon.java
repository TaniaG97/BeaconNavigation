package by.grsu.ftf.beaconlibrary.beacon;

/**
 * Created by Admin on 31.10.2017.
 */

public class Beacon {
    private String id;
    private int rssi;

    public Beacon(String name, int rssi) {
        this.id = name;
        this.rssi = rssi;
    }

    public String getId() {
        return id;
    }
    public int getRssi() {
        return rssi;
    }
}
