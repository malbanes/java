package com.avaj.simulator;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WeatherProvider {
    private static WeatherProvider INSTANCE = null;
    private static final String[] weather = { "SUN", "RAIN", "FOG", "SNOW"};

    private Map<String, String> map = new HashMap<String, String>();
    
    private WeatherProvider() {};

    public static WeatherProvider getProvider() {
        if (INSTANCE == null) {
            INSTANCE = new WeatherProvider();
        }
        return INSTANCE;
    }

    public String getCurrentWeather(Coordinates p_coordinates) {
        String key = Integer.toString(p_coordinates.getHeight())+
                    Integer.toString(p_coordinates.getLatitude())+
                    Integer.toString(p_coordinates.getLongitude());
                    
        if (!map.containsKey(key)) {
            map.put(key, weather[new Random().nextInt(4)]);
        }
        return(map.get(key));
    }
    
}