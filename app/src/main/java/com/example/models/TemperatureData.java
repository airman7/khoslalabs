package com.example.models;

import com.example.util.Utility;
import com.google.gson.annotations.SerializedName;

public class TemperatureData {
    Double temp;
    @SerializedName("feels_like")
    Double feelsLike;
    @SerializedName("temp_min")
    Double minTemp;
    @SerializedName("temp_max")
    Double maxTemp;
    Double humidity;

    @Override
    public String toString() {
        return "It is " + Utility.convertToCelcius(temp)
                +"°C with minimum "+ Utility.convertToCelcius(minTemp)
                + "°C and maximum going up to " + Utility.convertToCelcius(maxTemp)
                + "°C. " + humidity + "% humid";
    }

    public String getTemp() {
        return Utility.convertToCelcius(temp).toString();
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(Double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public Double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(Double minTemp) {
        this.minTemp = minTemp;
    }

    public Double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(Double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }
}
