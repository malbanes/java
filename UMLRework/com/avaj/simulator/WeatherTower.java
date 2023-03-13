package com.avaj.simulator;


public class WeatherTower extends Tower{
    
    public String getWeather(Coordinates p_coordinates){
        return(WeatherProvider.getProvider().getCurrentWeather(p_coordinates));
    }

    public void changeWeather() {
        this.conditionsChanged();
    }

}