package com.example.fainl_android.Activity;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.example.fainl_android.R;
import com.example.fainl_android.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        LatLng firstLatLang = new LatLng(31.498505, 34.4347907);
        LatLng SecondLatLang = new LatLng(31.515013, 34.438915);
        LatLng thirdLatLang = new LatLng(31.502827, 34.4179497);
        LatLng foLatLang = new LatLng(31.523057, 34.4352137);
        mMap.addMarker(new MarkerOptions().position(firstLatLang).title("UCAS"));
        mMap.addMarker(new MarkerOptions().position(SecondLatLang).title("الاسلامية"));
        mMap.addMarker(new MarkerOptions().position(foLatLang).title("الرشيد"));
        mMap.addMarker(new MarkerOptions().position(thirdLatLang).title("مسجد الشيخ عجلين"));
        mMap.addPolygon(new PolygonOptions().add(firstLatLang,thirdLatLang,foLatLang, SecondLatLang).
                strokeWidth(5f).strokeColor(Color.RED).fillColor(Color.GRAY));


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstLatLang, 15));
    }
}