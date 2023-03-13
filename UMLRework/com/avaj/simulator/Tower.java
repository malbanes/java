package com.avaj.simulator;

import com.avaj.simulator.aircraft.*;
import java.util.ArrayList;
import java.util.List;


public class Tower{
	private List<Flyable> observers;

	public void register(Flyable p_flyable){
        if (this.observers == null) {
            this.observers = new ArrayList<>();
        }
        System.out.println("Tower says: " + p_flyable.getName() + " registered to weather tower.");
        this.observers.add(p_flyable);
    }
	public void unregister(Flyable p_flyable){
        System.out.println("Tower says: " + p_flyable.getName() + " unregistered from weather tower.");
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
}
