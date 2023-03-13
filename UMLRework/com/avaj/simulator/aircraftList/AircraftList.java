package com.avaj.simulator.aircraftList;

public class AircraftList {

    enum Type {
        JetPlane,
        Helicopter,
        Baloon,
      }

    //Instantiate your global variables
    private String type;
    private String name;
    private int longitude;
    private int latitude;
    private int height;



    public String getType() {
        return type;
    }



    public void setId(String type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLongitude() {
        return this.longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}