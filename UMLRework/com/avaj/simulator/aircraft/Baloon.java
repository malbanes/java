
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
            System.out.println(this.getName() + ": Finaly ! Someone pass me the Solar cream !");
            try { coordinates.alterLongitude(2); }
            catch(CustomExceptions c) {
               System.out.println(c);
               System.exit(0);
            }
            coordinates.alterHeight(4);
            break;
         case "RAIN":
            System.out.println(this.getName() + ": I'm siinging in the rain..");
            coordinates.alterHeight(-5);
            break;
         case "FOG":
            System.out.println(this.getName() + ": I can't see ! Watch out for the birds !!");
            coordinates.alterHeight(-3);
            break;
         case "SNOW":
            System.out.println(this.getName() + ": It's snowing. We're gonna crash.");
            coordinates.alterHeight(-15);
            break;
      }
      if (coordinates.getHeight() <= 0) {
         System.out.println(this.getName() + " landing.");
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