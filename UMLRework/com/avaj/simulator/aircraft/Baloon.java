
package com.avaj.simulator.aircraft;

import com.avaj.simulator.*;
import com.avaj.simulator.exceptions.CustomExceptions;


public class Baloon extends Aircraft implements Flyable {
   
   protected WeatherTower weatherTower;
   private final String type = "Baloon";

   public Baloon(long p_id, String p_name, Coordinates p_coordinates){
      super(p_id, p_name, p_coordinates);
   }
   @Override
   public void updateCondition() {
      String weather = this.weatherTower.getWeather(this.coordinates);
      switch(weather) {
         case "SUN":
            weatherTower.logMessage(this.getName() + ": Finaly ! Someone pass me the Solar cream !");
            try { coordinates.alterLongitude(2); }
            catch(CustomExceptions c) {
               System.out.println(c);
               System.exit(0);
            }
            this.coordinates.alterHeight(4);
            break;
         case "RAIN":
            weatherTower.logMessage(this.getName() + ": I'm siinging in the rain..");
            this.coordinates.alterHeight(-5);
            break;
         case "FOG":
            weatherTower.logMessage(this.getName() + ": I can't see ! Watch out for the birds !!");
            this.coordinates.alterHeight(-3);
            break;
         case "SNOW":
            weatherTower.logMessage(this.getName() + ": It's snowing. We're gonna crash.");
            coordinates.alterHeight(-15);
            break;
      }
      if (this.coordinates.getHeight() <= 0) {
         weatherTower.logMessage(this.getName() + " landing.");
         weatherTower.unregister(this);
      }
      return;
   }
   @Override
   public void registerTower(WeatherTower p_weatherTower){
      this.weatherTower = p_weatherTower;
   }

   @Override
   public String getName() {
      return this.aircraftToString(this.type);
   }
}