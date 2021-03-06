package com.epam.automation.javacollections.taxistation.cars;


public class FreightTaxi extends Vehicle {
    int loadCapacity;

    public FreightTaxi() {
    }

    public FreightTaxi(String make, String model) {
        super(make, model);
    }

    public FreightTaxi(String make, String model, int fuelConsumption, int maxSpeed, int loadCapacity) {
        super(make, model, fuelConsumption, maxSpeed);
        this.loadCapacity = loadCapacity;
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(int loadCapacity) {
        this.loadCapacity = loadCapacity;
    }
}

