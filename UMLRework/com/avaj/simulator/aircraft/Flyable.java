
package com.avaj.simulator.aircraft;

import com.avaj.simulator.*;

public abstract class Flyable {

    protected WeatherTower weatherTower;

    public abstract void updateCondition();
    
    public void registerTower(WeatherTower p_tower) {
        this.weatherTower = p_tower;
        p_tower.register(this);
    }

    public abstract String getName();
}