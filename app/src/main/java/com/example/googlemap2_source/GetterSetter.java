package com.example.googlemap2_source;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

import java.io.Serializable;

class GetterSetter implements ClusterItem, Serializable {
    String heello;
    LatLng latLng;

    public String getHeello() {
        return heello;
    }

    public void setHeello(String heello) {
        this.heello = heello;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public GetterSetter (String heello, double latitude, double longtitude)
    {
        this.heello = heello;
        this.latLng = new LatLng(latitude, longtitude);
    }

    @Override
    public LatLng getPosition() {
        return latLng;
    }

    @Override
    public String getTitle() {
        return heello;
    }

    @Override
    public String getSnippet() {
        return null;
    }
}
