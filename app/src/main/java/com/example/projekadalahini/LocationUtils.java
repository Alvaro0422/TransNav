package com.example.projekadalahini;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

public class LocationUtils {

    public interface LocationListener {
        void onLocationReceived(Location location);
    }

    public static void getLastKnownLocation(Context context, LocationListener listener) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        try {
            locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, new android.location.LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    listener.onLocationReceived(location);
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                }

                @Override
                public void onProviderEnabled(String provider) {
                }

                @Override
                public void onProviderDisabled(String provider) {
                }
            }, null);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }
}
