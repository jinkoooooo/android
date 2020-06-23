package com.example.googlemap2_source;

import android.content.Context;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

class MapCluster extends DefaultClusterRenderer<GetterSetter>{

    Context mContext;

    @Override
    protected void onBeforeClusterItemRendered(GetterSetter item, MarkerOptions markerOptions) {
        super.onBeforeClusterItemRendered(item, markerOptions);
    }

    public MapCluster(Context context, GoogleMap map, ClusterManager<GetterSetter> clusterManager) {
        super(context, map, clusterManager);
        this.mContext = context;
    }

    @Override
    protected boolean shouldRenderAsCluster(Cluster<GetterSetter> cluster) {
        return cluster.getSize() > 1;
    }
}
