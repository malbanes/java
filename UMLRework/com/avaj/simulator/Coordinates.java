package com.avaj.simulator;
import com.avaj.simulator.exceptions.CustomExceptions;

public class Coordinates{
	private int longitude;
	private int latitude;
	private int height;

	Coordinates(int p_longitude, int p_latitude, int p_height){
		this.longitude = p_longitude;
		this.latitude = p_latitude;
		this.height = p_height;
	}

	public int getLongitude(){
		return this.longitude;
	}

	public int getLatitude(){
		return this.latitude;
	}

	public int getHeight(){
		return this.height;
	}

	public void alterLongitude(int p_longitude) {
		this.longitude = this.longitude + p_longitude;
		if (this.longitude < 0) {
			throw new CustomExceptions("Max Integer overflow: Out of the map");
		}
	}

	public void alterLatitude(int p_latitude){
		this.latitude = this.latitude + p_latitude;
		if (this.latitude < 0) {
			throw new CustomExceptions("Max Integer overflow: Out of the map");
		}
	}

	public void alterHeight(int p_height){
		if (this.height + p_height >= 100) {
			this.height = 100;
		}
		else {
			this.height = this.height + p_height;
		}
	}

}
