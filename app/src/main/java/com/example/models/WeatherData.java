package com.example.models;

import com.google.gson.annotations.SerializedName;

public class WeatherData {
    String name;
    @SerializedName("main")
    TemperatureData temperatureData;
    @SerializedName("dt")
    Long date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TemperatureData getTemperatureData() {
        return temperatureData;
    }

    public void setTemperatureData(TemperatureData temperatureData) {
        this.temperatureData = temperatureData;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return temperatureData.toString() + " in " + getName();
    }
}
