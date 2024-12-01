package com.avaj.simulator.aircraft;

import com.avaj.simulator.*;
import com.avaj.simulator.exceptions.CustomExceptions;

public class Helicopter extends Aircraft {
   
   private final String type = "Helicopter";

   public Helicopter(long p_id, String p_name, Coordinates p_coordinates){
      super(p_id, p_name, p_coordinates);
   }
   @Override
   public void updateCondition() {
      String weather = this.weatherTower.getWeather(this.coordinates);
      switch(weather) {
         case "SUN":
            weatherTower.logMessage(this.getName() + ": It's so hot ! Open the carrier.");
            try { coordinates.alterLongitude(10); }
            catch(CustomExceptions c) {
               System.out.println(c);
               System.exit(0);
            }
            coordinates.alterHeight(2);
            break;
         case "RAIN":
            weatherTower.logMessage(this.getName() + ": It's raining. Better watch out for lightings");
            try { coordinates.alterLongitude(5); }
            catch(CustomExceptions c) {
               System.out.println(c);
               System.exit(0);
            }
            break;
         case "FOG":
            weatherTower.logMessage(this.getName() + ": Carefull. We enter the fog.");
            try { coordinates.alterLongitude(1); }
            catch(CustomExceptions c) {
               System.out.println(c);
               System.exit(0);
            }
            break;
         case "SNOW":
            weatherTower.logMessage(this.getName() + ": My rotor is going to freeze!");
            coordinates.alterHeight(-12);
            break;
      }
      if (coordinates.getHeight() <= 0) {
         weatherTower.logMessage(this.getName() + " landing.");
         weatherTower.unregister(this);
      }
      return;
   }
   @Override
   public void registerTower(WeatherTower p_weatherTower){
      this.weatherTower = p_weatherTower;
      this.weatherTower.register(this);
   }
   
   public String getName() {
      return this.aircraftToString(this.type);
   }
}