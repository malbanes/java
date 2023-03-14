package com.avaj.simulator;

import com.avaj.simulator.aircraft.*;
import java.util.ArrayList;
import java.util.List;


public class Tower{
	private List<Flyable> observers;
    private ArrayList<String> logMessages = new ArrayList<String>();

	public void register(Flyable p_flyable){
        if (this.observers == null) {
            this.observers = new ArrayList<Flyable>();
        }
        this.logMessage("Tower says: " + p_flyable.getName() + " registered to weather tower.");
        this.observers.add(p_flyable);
    }
	public void unregister(Flyable p_flyable){
        this.logMessage("Tower says: " + p_flyable.getName() + " unregistered from weather tower.");
        this.observers.remove(p_flyable);
    }

    public List<Flyable> getObservers(){
        return this.observers;
    }

	protected void conditionsChanged(){
        List<Flyable> tmpObservers = new ArrayList<Flyable>();
        tmpObservers.addAll(this.observers);
        tmpObservers.forEach((aircraft) -> {
            aircraft.updateCondition();
        });
    };

    public void logMessage(String message) {
        this.logMessages.add(message);
    }

    public ArrayList<String> getLogMessages() {
        return this.logMessages;
    }
}
