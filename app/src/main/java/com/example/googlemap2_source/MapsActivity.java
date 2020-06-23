package com.example.googlemap2_source;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.googlemap2_source.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MapsActivity extends AppCompatActivity
        implements OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback,GoogleMap.OnCameraIdleListener{
    private GoogleMap mMap;

    private static final String TAG = "googlemap_example";

    ArrayList<GetterSetter2> arrayList = new ArrayList<>();
    ArrayList<GetterSetter> list = new ArrayList<>();

    private ClusterManager<GetterSetter> manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //API
        setContentView(R.layout.activity_maps);
        arrayList = (ArrayList<GetterSetter2>) getIntent().getSerializableExtra("arrayList");

        for(int i =0; i< arrayList.size(); i++){
            list.add(new GetterSetter(arrayList.get(i).getName(), arrayList.get(i).getLatitude(), arrayList.get(i).getLongtitude()));
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        Button listbtn = findViewById(R.id.golist);
        listbtn.bringToFront();
        listbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent2);
            }
        });



    }


    @Override
    public void onMapReady(final GoogleMap googleMap) {
        Log.d(TAG, "onMapReady :");

        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.559975221378, 126.975312652739), mMap.getCameraPosition().zoom+3));
        mMap.setOnCameraIdleListener(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        manager = new ClusterManager<>(this, mMap);
        manager.setRenderer(new MapCluster(this, mMap, manager));
        manager.setAnimation(true);

        for(int i =0; i< arrayList.size(); i++){
            Log.d(TAG, "Receive DATA " + arrayList.get(i).getName());
        }

        manager.addItems(list);
        manager.cluster();


        manager.setOnClusterClickListener(new ClusterManager.OnClusterClickListener<GetterSetter>() {
            @Override
            public boolean onClusterClick(Cluster<GetterSetter> cluster) {

                //클러스트가 되어있는 아이템을 클릭했을때
                if (cluster.getSize() > 9)
                {
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cluster.getPosition(), mMap.getCameraPosition().zoom + 3));
                }
                return false;
            }
        });

        manager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<GetterSetter>() {
            @Override
            public boolean onClusterItemClick(GetterSetter item) {

                //아이템 1개 클릭할때
                return false;
            }
        });

    }

    @Override
    public void onCameraIdle() {

        //맵을 손으로 움직일때, 제일마지막에 맵이 멈췄을때 작동하는 공간
        Log.d("Map : ", "is Moved");

        if (manager!=null)
            manager.cluster();
    }

}