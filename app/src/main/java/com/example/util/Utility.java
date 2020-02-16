package com.example.util;

import java.text.DecimalFormat;

public class Utility {
    public static final String weatherAPIKey ="f39829c2c743d6ac953c40dca6824018";
    public static final String weatherURL = "https://api.openweathermap.org/data/2.5/weather?lat=%1$s&lon=%2$s&appid=%3$s";
    private static DecimalFormat df = new DecimalFormat("#.#");

    public static Double convertToCelcius(Double temp){
        temp = temp-273.15;
        return Double.valueOf(df.format(temp));
    }
}
