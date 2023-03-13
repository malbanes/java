
package com.avaj.simulator.aircraft;

import com.avaj.simulator.*;
import com.avaj.simulator.exceptions.CustomExceptions;



public class JetPlane extends Aircraft implements Flyable {

   private WeatherTower weatherTower;
   private final String type = "JetPlane";

   public JetPlane(long p_id, String p_name, Coordinates p_coordinates){
      super(p_id, p_name, p_coordinates);
   }
   @Override
   public void updateCondition() {
      String weather = this.weatherTower.getWeather(this.coordinates);

      switch(weather) {
         case "SUN":
            System.out.println(this.getName() + ": It's sunny ! Let me put my Ray-Ban on.");
            try { coordinates.alterLatitude(10); }
            catch(CustomExceptions c) {
               System.out.println(c);
               System.exit(0);
            }
            coordinates.alterHeight(2);
            break;
         case "RAIN":
            System.out.println(this.getName() + ": Rain is nothing.");
            try { coordinates.alterLatitude(5); }
            catch(CustomExceptions c) {
               System.out.println(c);
               System.exit(0);
            }
            break;
         case "FOG":
            System.out.println(this.getName() + ": I can't see. Start the autopilot ! Ho wait.. It was started from the biginning.");
            try { coordinates.alterLatitude(1); }
            catch(CustomExceptions c) {
               System.out.println(c);
               System.exit(0);
            }
            break;
         case "SNOW":
            System.out.println(this.getName() + ": It's Freezing, Avoid the ice cloud sword.");
            coordinates.alterHeight(-7);
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