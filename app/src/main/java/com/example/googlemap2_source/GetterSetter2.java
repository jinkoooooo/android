package com.example.googlemap2_source;

import java.io.Serializable;

public class GetterSetter2 implements Serializable {
    String Name;

    public double getLatitude() {
        return latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    double latitude;
    double longtitude;

    public String getName() {
        return Name;
    }

    public GetterSetter2(String Name, double latitude, double longtitude)
    {
        this.Name = Name;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }
}