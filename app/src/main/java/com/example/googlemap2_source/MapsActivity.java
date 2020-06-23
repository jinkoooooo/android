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
        ActivityCompat.OnRequestPermissionsResultCallback {
    private GoogleMap mMap;

    private static final String TAG = "googlemap_example";



    ArrayList<Double> arrX = new ArrayList<>();
    ArrayList<Double> arrY = new ArrayList<>();
    ArrayList<String> arrN = new ArrayList<>();
    ArrayList<String> arrS = new ArrayList<>();

    ArrayList<GetterSetter> arrayList = new ArrayList<>();

    private ClusterManager<GetterSetter> manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

/*
        //API
        setContentView(R.layout.activity_maps);
        Intent intent = new Intent(this, LoadingActivity.class);

        startActivity(intent);*/
/*

        Button listbtn = findViewById(R.id.golist);
        listbtn.bringToFront();
        listbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent2);
            }
        });
*/


    }



    @Override
    public void onMapReady(final GoogleMap googleMap) {
        Log.d(TAG, "onMapReady :");

        mMap = googleMap;
        manager = new ClusterManager<>(this, mMap);
        manager.setRenderer(new MapCluster(this, mMap, manager));
        manager.setAnimation(true);

        //arraylist를 이제 manager 다넣고 cluster 하면 이제 새로그침되서 맵에 보임
        manager.addItems(arrayList);
        manager.cluster();

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


        arrayList.add(new GetterSetter("숭례문",37.559975221378,126.975312652739));
        arrayList.add(new GetterSetter("원각사지 십층 석탑",37.5715461695449,126.988207994364));
        arrayList.add(new GetterSetter("서울 북한산 신라 진흥왕 순수비",37.5240413763397,126.980350241652));
        arrayList.add(new GetterSetter("여주 고달사지 승탑",37.3939072455202,127.652160778359));
        arrayList.add(new GetterSetter("보은 법주사 쌍사자 석등",36.5421679403972,127.833219375596));
        arrayList.add(new GetterSetter("충주 탑평리 칠층석탑",37.0158885404695,127.866630082822));
        arrayList.add(new GetterSetter("천안 봉선홍경사 갈기비",36.943151950305,127.134710477185));
        arrayList.add(new GetterSetter("보령 성주사지 낭혜화상탑비",36.3446996913308,126.655469090758));
        arrayList.add(new GetterSetter("부여 정림사지 오층석탑",36.2792973520136,126.913363436976));
        arrayList.add(new GetterSetter("남원 실상사 백장암 삼층석탑",35.4425391241814,127.619971568516));

        manager.addItems(arrayList);
        manager.cluster();

        /*for (int idx = 0; idx < arrX.size(); idx++) {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(arrX.get(idx), arrY.get(idx)))
                    .title(arrN.get(idx)) // 타이틀.
                    .snippet("국보" + (idx+1) + "호");

            mMap.addMarker(markerOptions);
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.52487, 126.92723), 8));
*/
    }
}