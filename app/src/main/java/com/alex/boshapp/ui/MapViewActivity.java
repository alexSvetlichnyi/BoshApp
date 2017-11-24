package com.alex.boshapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alex.boshapp.R;
import com.alex.boshapp.datasource.City;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.parceler.Parcels;

public class MapViewActivity extends AppCompatActivity {
    public static final String CITY = "city";

    private LatLng latLng = null;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_view_layout);

        ((MapFragment) getFragmentManager().findFragmentById(R.id.map_view)).getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                bundle = getIntent().getExtras();

                if (bundle != null && bundle.containsKey(CITY)) {
                    City city = Parcels.unwrap(bundle.getParcelable(CITY));
                    Double lat = Double.valueOf(city.getLatitude());
                    Double lon = Double.valueOf(city.getLongitude());
                    latLng = (new LatLng(lat, lon));
                    googleMap.clear();
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(latLng);
                    markerOptions.title(city.getCity());
                    googleMap.addMarker(markerOptions);
                }
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(8), 2000, null);
            }
        });
    }
}
