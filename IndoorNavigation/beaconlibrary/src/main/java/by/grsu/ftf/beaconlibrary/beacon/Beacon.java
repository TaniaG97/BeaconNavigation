package by.grsu.ftf.beaconlibrary.beacon;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Admin on 31.10.2017.
 */

public class Beacon implements Parcelable {
    private String id;
    private int rssi;

    public Beacon(String name, int rssi) {
        this.id = name;
        this.rssi = rssi;
    }

    protected Beacon(Parcel in) {
        id = in.readString();
        rssi = in.readInt();
    }

    public static final Creator<Beacon> CREATOR = new Creator<Beacon>() {
        @Override
        public Beacon createFromParcel(Parcel in) {
            return new Beacon(in);
        }

        @Override
        public Beacon[] newArray(int size) {
            return new Beacon[size];
        }
    };

    public String getId() {
        return id;
    }
    public int getRssi() {
        return rssi;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeInt(rssi);
    }
}
