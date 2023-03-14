package com.avaj.simulator;

import com.avaj.simulator.aircraft.*;
import com.avaj.simulator.exceptions.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;



public class Simulator {

	//Check Entry File
	public static boolean parseEntry (String entry) {
		if (entry.isEmpty()){
			throw new WrongFormatException(null);
		}
		//check no empty newline
		if (!Character.isDigit(entry.charAt(entry.length() - 2))) {
			throw new WrongFormatException(null);
		}

		String[] lines = entry.split(System.lineSeparator());

		try { Integer.parseInt(lines[0]); }
		catch (NumberFormatException e) {
			throw new WrongFormatException(e);
		}
		if ( Integer.parseInt(lines[0]) < 1) {
			throw new WrongFormatException(null);
		}
		
		for(int i = 1; i< lines.length; i++){
			if (lines[i].isEmpty()){
				throw new WrongFormatException(null);
			}
			if (lines[i].charAt(lines[i].length() - 1) == ' ') {
				throw new WrongFormatException(null);
			}

			String[] aircraft = lines[i].split(" ");
			if (aircraft.length != 5){
				throw new WrongFormatException(null);
			}
			//Check TYPE
			String type = aircraft[0];
			if (!type.equals("JetPlane") && !type.equals("Helicopter") && !type.equals("Baloon") ) {
				throw new WrongFormatException(null);
			}
			//Check Name
			if (type.charAt(0) != aircraft[1].charAt(0)) {
				throw new WrongFormatException(null);
			}
			String nameId = aircraft[1].substring(1);
			//Check all int
			try {
				Integer.parseInt(nameId);
				Integer.parseInt(aircraft[2]);
				Integer.parseInt(aircraft[3]);
				Integer.parseInt(aircraft[4]);
			}
			catch (NumberFormatException e) {
				throw new WrongFormatException(e);
			}

			//Check all Int Only number Pattern
			String regex = "^[0-9]+$";
			if (!nameId.matches(regex) || !aircraft[2].matches(regex) || !aircraft[3].matches(regex) || !aircraft[4].matches(regex)) {
				throw new WrongFormatException(null);
			}

			//Check height
			if ( Integer.parseInt(aircraft[3]) < 0 || Integer.parseInt(aircraft[3]) > 100) {
				throw new WrongFormatException(null);
			}
		}
		return true;
	}

	private static BufferedReader readFailingFile(String pathFile) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File(pathFile)));
        return br;
    }

	public static void main(String[] args) {
		String everything = "";
		String line = "";
		FileReader fileReader = null;
		File file = null;

		if (args.length != 1) { 
			System.out.println("This program take exactly One argument");  
			return;
		}
		//Open and Read entry simulation file
		BufferedReader br = null;
		try {
			br = readFailingFile(args[0]);
		}
		catch (IOException e){
			System.out.println("file doesn't exist");
			return;
		}

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

		//Parse entry
		boolean mapping = false;
		try { mapping = parseEntry(everything); }
        catch(WrongFormatException c) {
               System.out.println(c);
        }
		if (mapping == false) {
			return;
		}

		//Formater la data
		String aircraftFactory[] = everything.split("\\n"); 

		int numberOfTurn = Integer.parseInt(aircraftFactory[0]);
		AircraftFactory aircraftFac = new AircraftFactory();
		WeatherTower weatherTower = new WeatherTower();

		for (int i=1; i< aircraftFactory.length; i++) {
			String oneLine[] = aircraftFactory[i].split(" ");
			Coordinates oneCoordinate = new Coordinates(Integer.parseInt(oneLine[2]), Integer.parseInt(oneLine[3]), Integer.parseInt(oneLine[4]));
			Flyable flyable = aircraftFac.newAircraft(oneLine[0], oneLine[1], oneCoordinate);
			weatherTower.register(flyable);
			flyable.registerTower(weatherTower);
		}

		List<Flyable> flyableList = weatherTower.getObservers();
		 for(int i = 0; i < numberOfTurn; i++){
			weatherTower.changeWeather();
		 }

		 //Generate simulation file
		 System.out.println(weatherTower.getLogMessages());

	}

}
