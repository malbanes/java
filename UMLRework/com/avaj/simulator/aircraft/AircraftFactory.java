package com.avaj.simulator.aircraft;

import com.avaj.simulator.Coordinates;
import com.avaj.simulator.exceptions.CustomExceptions;


public final class AircraftFactory{
    private static long idCounter = 0;

    private static long nextId() {
        // retourne l'ID suivant
        idCounter++;
        return (idCounter);
    }

    private static AircraftFactory instance = new AircraftFactory();

    private AircraftFactory() {};

    public static AircraftFactory getInstance() {
        return instance;
    }

    public static Flyable newAircraft( String p_type, String p_name, Coordinates p_coordinates)
    {
        if (p_type == null || p_type.isEmpty())
            return null;
        switch (p_type) {
        case "Helicopter":
            return new Helicopter(nextId(), p_name, p_coordinates);
        case "Baloon":
            return new Baloon(nextId(), p_name, p_coordinates);
        case "JetPlane":
            return new JetPlane(nextId(), p_name, p_coordinates);
        default:
            throw new CustomExceptions("Unknown type: " + p_type);
        }
    }
}