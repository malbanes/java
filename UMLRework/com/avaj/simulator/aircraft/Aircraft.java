package com.avaj.simulator.aircraft;

import com.avaj.simulator.Coordinates;

public class Aircraft extends Flyable {
	protected long id;
	protected String name;
	protected Coordinates coordinates;

	@Override
	public void updateCondition() {
		this.weatherTower.logMessage("aircraft is not defined");
	}

	protected Aircraft(long p_id, String p_name, Coordinates p_coordinates) {
	   // initialisation du nom et des coordonnées
	   this.id = p_id;
	   this.name = p_name;
	   this.coordinates = p_coordinates;
	}

	protected String aircraftToString(String p_type) {
		return p_type + "#" + this.name + "(" + this.id + ")";
	}

	@Override
	public String getName(){
		return this.name;
	}

 }