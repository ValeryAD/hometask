package com.epam.automation.javacollections.taxistation.actions;

import com.epam.automation.javacollections.taxistation.cars.Vehicle;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Properties;

public class TaxiStation {
    private List<? extends Vehicle> vehicles;

    public TaxiStation(List<? extends Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public BigDecimal countVehiclesValue() {
        BigDecimal vehiclesValue = new BigDecimal("0");
        vehiclesValue.setScale(2, RoundingMode.HALF_UP);
        Properties pricesByModel = new PropertiesLoader().getPrices();
        for (Vehicle vehicle : vehicles) {
            vehiclesValue = vehiclesValue.add(new BigDecimal((String) pricesByModel.get(vehicle.getModel())));
        }
        return vehiclesValue;
    }

    public List<? extends Vehicle> getVehicles() {
        return vehicles;
    }

    public int getLowestMaxSpeedOfAllVehicles() {
        int minSpeed = vehicles.get(0).getMaxSpeed();
        for (Vehicle vehicle : vehicles) {
            minSpeed = Math.min(vehicle.getMaxSpeed(), minSpeed);
        }
        return minSpeed;
    }

    public int getHighestMaxSpeedOfAllVehicles() {
        int maxSpeed = vehicles.get(0).getMaxSpeed();
        for (Vehicle vehicle : vehicles) {
            maxSpeed = Math.max(vehicle.getMaxSpeed(), maxSpeed);
        }
        return maxSpeed;
    }

    public Vehicle findVehicleBySpeedRange(int minSpeed, int maxSpeed) {
        Vehicle vehicleResult = null;

        for (Vehicle vehicle : vehicles) {
            if (vehicle.getMaxSpeed() >= minSpeed && vehicle.getMaxSpeed() <= maxSpeed) {
                vehicleResult = vehicle;
                break;
            }
        }

        return vehicleResult;
    }
}
