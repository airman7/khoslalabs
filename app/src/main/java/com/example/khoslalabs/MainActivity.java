package com.example.khoslalabs;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.models.ForecastData;
import com.example.models.WeatherData;
import com.example.network.SingletonRequestQueue;
import com.example.util.Utility;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends Activity implements LocationListener {

    LocationManager locationManager;
    TextView currentTemp, extraDetails,day1,day2,day3,day4,day5;
    Double latitude, longitude;
    int PERMISSION_ACCESS_FINE_LOCATION = 14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentTemp = findViewById(R.id.currentTemp);
        extraDetails = findViewById(R.id.extra);
        day1 = findViewById(R.id.day1);
        day2 = findViewById(R.id.day2);
        day3 = findViewById(R.id.day3);
        day4 = findViewById(R.id.day4);
        day5 = findViewById(R.id.day5);

        // Instantiate the location service
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED )
        {
            ActivityCompat.requestPermissions(
                    this,
                    new String [] { android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION },
                    PERMISSION_ACCESS_FINE_LOCATION
            );
        }
        locationManager.requestSingleUpdate(setupCriteria(), this, null);
    }

    public void requestAndSetWeather(){
        // Instantiate the RequestQueue.
        final String weatherUrl = String.format(Utility.weatherURL, latitude, longitude, Utility.weatherAPIKey);
        final String forecastUrl = String.format(Utility.weatherForecastURL, latitude, longitude, Utility.weatherAPIKey);
        RequestQueue queue = SingletonRequestQueue.getInstance(getApplicationContext()).getRequestQueue();

        // Request a string response from the provided URL.
        StringRequest weatherRequest = new StringRequest(Request.Method.GET, weatherUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        GsonBuilder builder = new GsonBuilder();
                        Gson mGson = builder.create();
                        WeatherData data = mGson.fromJson(response, WeatherData.class);

                        currentTemp.setText(data.getName() + ": " + data.getTemperatureData().getTemp()+"°C");
                        extraDetails.setText(data.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Some error occurred. Please try later.",Toast.LENGTH_LONG);
            }
        });


        // Request a string response from the provided URL.
        StringRequest forecastRequest = new StringRequest(Request.Method.GET, forecastUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        GsonBuilder builder = new GsonBuilder();
                        Gson mGson = builder.create();
                        ForecastData forecastData = mGson.fromJson(response, ForecastData.class);

                        day1.setText(getDate(forecastData.getForecast().get(6).getDate()) + " : " + forecastData.getForecast().get(6).getTemperatureData().getTemp()+"°C");
                        day2.setText(getDate(forecastData.getForecast().get(14).getDate()) + " : " + forecastData.getForecast().get(14).getTemperatureData().getTemp()+"°C");
                        day3.setText(getDate(forecastData.getForecast().get(22).getDate()) + " : " + forecastData.getForecast().get(22).getTemperatureData().getTemp()+"°C");
                        day4.setText(getDate(forecastData.getForecast().get(30).getDate()) + " : " + forecastData.getForecast().get(30).getTemperatureData().getTemp()+"°C");
                        day5.setText(getDate(forecastData.getForecast().get(38).getDate()) + " : " + forecastData.getForecast().get(38).getTemperatureData().getTemp()+"°C");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Some error occurred. Please try later.",Toast.LENGTH_LONG);
            }
        });

        // Add the request to the RequestQueue.
        queue.add(weatherRequest);
        queue.add(forecastRequest);
    }

    public Criteria setupCriteria(){
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(true);
        criteria.setHorizontalAccuracy(Criteria.ACCURACY_HIGH);
        criteria.setVerticalAccuracy(Criteria.ACCURACY_HIGH);
        return criteria;
    }

    public String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("dd MMM", cal).toString();
        return date;
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        requestAndSetWeather();
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
}
