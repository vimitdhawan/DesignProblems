package com.problem.renting.system.vehicle;

import com.problem.renting.system.constants.CarType;

import java.time.LocalDateTime;

public class Car extends Vehicle {

    private CarType type;

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public Car(String barcode, int vehicleId, String modelNo, String licenseNumber, LocalDateTime manufacturingDate, CarType type) {
        super(barcode, vehicleId, modelNo, licenseNumber, manufacturingDate);
        this.type = type;
    }


}
