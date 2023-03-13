
package com.avaj.simulator.aircraft;

import com.avaj.simulator.*;

public abstract interface Flyable {

    public abstract void updateCondition();
    
    public void registerTower(WeatherTower p_tower);
    
    public String getName();
}