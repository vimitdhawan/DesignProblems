package com.problem.renting.system.vehicle;


import com.problem.renting.system.constants.LogType;
import com.problem.renting.system.constants.VehicleStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Vehicle {
    String barcode;
    int vehicleId;
    String modelNo;
    String licenseNumber;
    LocalDateTime manufacturingDate;
    List<VehicleLog> vehicleLogs;
    VehicleStatus status;

    public Vehicle(String barcode, int vehicleId, String modelNo, String licenseNumber, LocalDateTime manufacturingDate) {
        this.barcode = barcode;
        this.vehicleId = vehicleId;
        this.modelNo = modelNo;
        this.licenseNumber = licenseNumber;
        this.manufacturingDate = manufacturingDate;
        VehicleLog log = new VehicleLog(1, "", LogType.OTHER, LocalDateTime.now());
        vehicleLogs = new ArrayList<>();
        vehicleLogs.add(log);
        status = VehicleStatus.AVAILABLE;
    }

    public void addVehicleLog(VehicleLog vehicleLog){
        vehicleLogs.add(vehicleLog);
    }

    public List<VehicleLog> getVehicleLogs() {
        return Collections.unmodifiableList(vehicleLogs);
    }
}
