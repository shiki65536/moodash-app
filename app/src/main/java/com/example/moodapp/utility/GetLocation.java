package com.example.moodapp.utility;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class GetLocation implements LocationListener {

    private LocationManager locationManager;
    private Context context;
    private OnLocationUpdateListener locationUpdateListener;

    public GetLocation(Context context, OnLocationUpdateListener locationUpdateListener) {
        this.context = context;
        this.locationUpdateListener = locationUpdateListener;
    }

    public void startLocationUpdates() {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // get user permission about getting the location information
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        //refresh the location every 3 seconds
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 3000, 0, this);
    }

    public void stopLocationUpdates() {
        if (locationManager != null) {
            locationManager.removeUpdates(this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        locationUpdateListener.onLocationUpdate(latitude, longitude);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onProviderDisabled(String provider) {}

    public interface OnLocationUpdateListener {
        void onLocationUpdate(double latitude, double longitude);
    }
}
