package com.example.projekadalahini;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.datatransport.runtime.Destination;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.maps.model.PolylineOptions;
import com.example.projekadalahini.DirectionsUtils;
import com.google.gson.annotations.SerializedName;

import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.Place.Field;
import com.google.android.libraries.places.api.model.PlaceTypes;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RouteActivity extends AppCompatActivity implements OnMapReadyCallback {

    String searched_place;
    String email;
    private GoogleMap mMap;
    private Button buttonDirection;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private static final int AUTOCOMPLETE_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        getSupportActionBar().hide();

        buttonDirection = findViewById(R.id.buttonDirection);
        buttonDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAutocompleteActivity();
            }
        });

        // Memeriksa izin lokasi
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            // Izin lokasi diberikan
            initializeMap();
        }
        Places.initialize(getApplicationContext(), "AIzaSyBz0T_9M8HMrMZ4r7_dn4Hm1A_6x5HFtKQ");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Izin lokasi diberikan
                initializeMap();
            } else {
                Toast.makeText(this, "Izin lokasi diperlukan.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initializeMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Menampilkan tombol My Location
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }

        // Menggeser peta ke lokasi pengguna saat ini
        LocationUtils.getLastKnownLocation(this, new LocationUtils.LocationListener() {
            @Override
            public void onLocationReceived(Location location) {
                LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));
            }
        });
    }

    private void getDirections(LatLng destinationLatLng) {
        // Mengambil posisi saat ini
        LocationUtils.getLastKnownLocation(this, new LocationUtils.LocationListener() {
            @Override
            public void onLocationReceived(Location location) {
                double currentLat = location.getLatitude();
                double currentLng = location.getLongitude();

                LatLng origin = new LatLng(currentLat, currentLng);
                LatLng destination = destinationLatLng;

                // Menggambar marker di posisi tujuan
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(destination).title("Destination"));

                // Menggambar polyline untuk arah dari posisi saat ini ke destinasi
                DirectionsUtils.getDirections(RouteActivity.this, origin, destination, new DirectionsUtils.DirectionsListener() {
                    @Override
                    public void onDirectionsReceived(List<LatLng> polyline) {
                        PolylineOptions polylineOptions = new PolylineOptions();
                        polylineOptions.addAll(polyline);
                        mMap.addPolyline(polylineOptions);
                    }
                });
            }
        });
    }

    public void addPolylineToMap(List<LatLng> polyline) {
        PolylineOptions polylineOptions = new PolylineOptions()
                .addAll(polyline)
                .width(10)
                .color(Color.RED);
        mMap.addPolyline(polylineOptions);
    }

    public void openAutocompleteActivity() {
        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG);
        Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                .setCountry("ID")
                .build(this);
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                Intent in = getIntent();
                Bundle bundle = in.getExtras();

                String emailSimpan = (String) bundle.get("email");
                searched_place= place.getName();
                saveUserData(emailSimpan, searched_place);
                LatLng destinationLatLng = place.getLatLng();
                getDirections(destinationLatLng);
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // Handle error
                Toast.makeText(this, "Error in autocomplete", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                // User canceled the operation
            }
        }
    }

    private void saveUserData(String email, String searched_place) {
        String historyData = email + "," + searched_place;
        File directory = getExternalFilesDir(null);

        if (directory != null) {
            File file = new File(directory, "history.txt");

            try {
                FileWriter fileWriter = new FileWriter(file, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(historyData);
                bufferedWriter.newLine();
                bufferedWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(RouteActivity.this, "Failed to save user data!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}