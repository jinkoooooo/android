package com.example.googlemap2_source;

import java.io.Serializable;

public class GetterSetter2 implements Serializable {
    String heello;

    public double getLatitude() {
        return latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    double latitude;
    double longtitude;

    public String getHeello() {
        return heello;
    }

    public GetterSetter2(String heello, double latitude, double longtitude)
    {
        this.heello = heello;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }
}