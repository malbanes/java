package com.avaj.simulator;

import com.avaj.simulator.aircraft.*;
import com.avaj.simulator.aircraftList.AircraftList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.*;



public class Simulator {

	//Check Entry File
	public static boolean parseEntry (String entry) {
		String[] lines = entry.split(System.lineSeparator());

		int maxWeatherChange = Integer.parseInt(lines[0]);
		if ( maxWeatherChange < 1) {
			return false;
		}
		AircraftList[] aircraftList = null;
		
		for(int i = 1; i< lines.length; i++){
			String[] aircraft = lines[i].split(" ");
			if (aircraft.length != 5){
				System.out.println("Wrong format lenght");  
				return false; 
			}
			// Create new aircraft
			AircraftList newAircraft = new AircraftList();

			String type = aircraft[0];
			//Check Type
			if (type.equals("JetPlane") || type.equals("Helicopter") || type.equals("Baloon") ) {
				newAircraft.setId(type);
			}
			else {
				System.out.println("Wrong format type");  
				return false; 
			}
			//Check Name
			// if (aircraft[1] == null || aircraft[1][0] != aircraft[0][0]){
			// 	System.out.println("Wrong format");  
			// 	return false; 
			// }
			newAircraft.setName(aircraft[1]);
			//Check longitude
			if ( Integer.parseInt(aircraft[2]) < 1 || Integer.parseInt(aircraft[2]) > 2147483647) {
				System.out.println("Wrong format - longitude");  
				return false;
			}
			newAircraft.setLongitude(Integer.parseInt(aircraft[2]));
			//Check latitude
			if ( Integer.parseInt(aircraft[2]) < 1 || Integer.parseInt(aircraft[2]) > 2147483647) {
				System.out.println("Wrong format - latitude");  
				return false;
			}
			newAircraft.setLatitude(Integer.parseInt(aircraft[3]));
			//Check height
			if ( Integer.parseInt(aircraft[3]) < 0 || Integer.parseInt(aircraft[3]) > 100) {
				System.out.println("Wrong format - height");  
				return false;
			}
			newAircraft.setHeight(Integer.parseInt(aircraft[4]));

			System.out.println(lines[i]);  

		}
		return true;
	}

	public static void main(String[] args) {
		String everything = "";
		String line = "";
		FileReader fileReader = null;
		if (args.length < 1) { 
			return;
		}
		//Read entry simulation file
		File file = new File(args[0]);
		try {
			fileReader = new FileReader(file);
		}
		catch (IOException e){
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fileReader);
		try {
			StringBuilder sb = new StringBuilder();
			try {
				line = br.readLine();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				try {
					line = br.readLine();
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}
			everything = sb.toString();
		} finally {
			try {
				br.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		if (everything == null || everything == "") {
			System.out.println("An error occur");
			return;
		}

		boolean mapping = parseEntry(everything);
		//Formater la data
		String aircraftFactory[] = everything.split("\\n"); 

		int numberOfTurn = Integer.parseInt(aircraftFactory[0]);
		AircraftFactory aircraftFac = new AircraftFactory();
		WeatherTower weatherTower = new WeatherTower();

		for (int i=1; i< aircraftFactory.length; i++) {
			String oneLine[] = aircraftFactory[i].split(" ");
			Coordinates oneCoordinate = new Coordinates(Integer.parseInt(oneLine[2]), Integer.parseInt(oneLine[3]), Integer.parseInt(oneLine[3]));

			Flyable flyable = aircraftFac.newAircraft(oneLine[0], oneLine[1], oneCoordinate);
			weatherTower.register(flyable);
			flyable.registerTower(weatherTower);
		}

		List<Flyable> flyableList = weatherTower.getObservers();
		 for(int i = 0; i < numberOfTurn; i++){
			weatherTower.changeWeather();
		 }

	}



}
