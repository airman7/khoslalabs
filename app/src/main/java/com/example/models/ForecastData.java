package com.example.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastData {
    @SerializedName("list")
    List<WeatherData> forecast;

    public List<WeatherData> getForecast() {
        return forecast;
    }

    public void setForecast(List<WeatherData> forecast) {
        this.forecast = forecast;
    }
}
