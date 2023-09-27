package com.example.projekadalahini;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DirectionsUtils {

    public interface DirectionsListener {
        void onDirectionsReceived(List<LatLng> polyline);
    }

    public static void getDirections(Context context, LatLng origin, LatLng destination, DirectionsListener listener) {
        String apiKey = "AIzaSyBz0T_9M8HMrMZ4r7_dn4Hm1A_6x5HFtKQ"; // Ganti dengan kunci API Google Maps Anda
        String url = "https://maps.googleapis.com/maps/api/directions/json?origin=" +
                origin.latitude + "," + origin.longitude +
                "&destination=" + destination.latitude + "," + destination.longitude +
                "&key=" + apiKey;

        new GetDirectionsTask(context, listener).execute(url);
    }

    private static class GetDirectionsTask extends AsyncTask<String, Void, List<LatLng>> {
        private Context context;
        private DirectionsListener listener;

        public GetDirectionsTask(Context context, DirectionsListener listener) {
            this.context = context;
            this.listener = listener;
        }

        @Override
        protected List<LatLng> doInBackground(String... strings) {
            List<LatLng> polyline = new ArrayList<>();

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();

                    JSONObject jsonObject = new JSONObject(response.toString());
                    JSONArray routes = jsonObject.getJSONArray("routes");
                    if (routes.length() > 0) {
                        JSONObject route = routes.getJSONObject(0);
                        JSONObject polylineObject = route.getJSONObject("overview_polyline");
                        String points = polylineObject.getString("points");
                        polyline = decodePolyline(points);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return polyline;
        }

        @Override
        protected void onPostExecute(List<LatLng> polyline) {
            if (polyline != null && polyline.size() > 0) {
                listener.onDirectionsReceived(polyline);

                // Panggil metode addPolylineToMap pada MainActivity
                ((RouteActivity) context).addPolylineToMap(polyline);
            } else {
                Toast.makeText(context, "Gagal mendapatkan petunjuk arah.", Toast.LENGTH_SHORT).show();
            }
        }


        private List<LatLng> decodePolyline(String encoded) {
            List<LatLng> polyline = new ArrayList<>();
            int index = 0;
            int length = encoded.length();
            int latitude = 0;
            int longitude = 0;

            while (index < length) {
                int b;
                int shift = 0;
                int result = 0;
                do {
                    b = encoded.charAt(index++) - 63;
                    result |= (b & 0x1F) << shift;
                    shift += 5;
                } while (b >= 0x20);
                int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
                latitude += dlat;

                shift = 0;
                result = 0;
                do {
                    b = encoded.charAt(index++) - 63;
                    result |= (b & 0x1F) << shift;
                    shift += 5;
                } while (b >= 0x20);
                int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
                longitude += dlng;

                LatLng point = new LatLng((latitude / 1E5), (longitude / 1E5));
                polyline.add(point);
            }

            return polyline;
        }
    }
}

