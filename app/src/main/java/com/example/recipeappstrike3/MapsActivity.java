package com.example.recipeappstrike3;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    LocationManager locationManager;
    LocationListener locationListener;
    LatLng userLatLng;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                //store user latLng
                /*userLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.clear();//clear old marker location
                mMap.addMarker(new MarkerOptions().position(userLatLng).title("Your Location"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(userLatLng));*/

                LatLng craiova = new LatLng(44.3, 23.79);
                mMap.addMarker(new MarkerOptions()
                        .position(craiova)
                        .title("Your Location"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(craiova));
            }
        };

        //asking for map permission
        askLocationPermission();

    }

    private void askLocationPermission() {
        Dexter.withContext(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        if (ActivityCompat.checkSelfPermission(getBaseContext(),
                                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                                && ActivityCompat.checkSelfPermission(getBaseContext(),
                                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        locationManager.requestLocationUpdates(LocationManager
                                .GPS_PROVIDER, 0, 0, locationListener);

                        //getting user's last known location
/*                        Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        userLatLng = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
                        mMap.clear();//clear old marker location
                        mMap.addMarker(new MarkerOptions().position(userLatLng).title("Your Location"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(userLatLng));*/

                        LatLng craiova = new LatLng(44.3, 23.79);
                        mMap.addMarker(new MarkerOptions()
                                .position(craiova)
                                .title("Your Location"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(craiova));
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }
}