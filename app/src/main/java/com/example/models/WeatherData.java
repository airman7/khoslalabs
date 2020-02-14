package com.example.models;

import com.google.gson.annotations.SerializedName;

public class WeatherData {
    String name;
    @SerializedName("main")
    TemperatureData temperatureData;

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

    @Override
    public String toString() {
        return temperatureData.toString() + " in " + getName();
    }
}
