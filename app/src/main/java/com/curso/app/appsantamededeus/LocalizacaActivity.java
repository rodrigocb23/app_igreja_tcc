package com.curso.app.appsantamededeus;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocalizacaActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizaca);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        MarkerOptions santa = new MarkerOptions()
                .position(new LatLng(-16.007047,-47.996701 ))
                .title("Santa");

       // LatLng santa = new LatLng( -16.007047,  -47.996701);
        //-16.007047, -47.996701

       /* mMap.addMarker(
                new MarkerOptions()
                        .position(santa)
                .title("Igraja Santa Mãe de Deus")


        ).showInfoWindow();*/

        mMap.addMarker(santa);

        //mMap.moveCamera(CameraUpdateFactory.newLatLng(santa));
    }
}
