package com.example.googlemap2_source;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

import java.io.Serializable;

class GetterSetter implements ClusterItem, Serializable {
    String Name;
    LatLng latLng;

    public String getHeello() {
        return Name;
    }

    public void setHeello(String Name) {
        this.Name = Name;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public GetterSetter (String Name, double latitude, double longtitude)
    {
        this.Name = Name;
        this.latLng = new LatLng(latitude, longtitude);
    }

    @Override
    public LatLng getPosition() {
        return latLng;
    }

    @Override
    public String getTitle() {
        return Name;
    }

    @Override
    public String getSnippet() {
        return null;
    }
}
