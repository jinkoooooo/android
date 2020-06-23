package com.example.googlemap2_source;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

class MapCluster extends DefaultClusterRenderer<GetterSetter>{


    public MapCluster(Context context, GoogleMap map, ClusterManager<GetterSetter> clusterManager) {
        super(context, map, clusterManager);
    }
}
