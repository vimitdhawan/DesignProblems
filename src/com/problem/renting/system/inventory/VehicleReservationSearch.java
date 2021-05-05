package com.problem.renting.system.inventory;

import com.problem.renting.system.vehicle.VehicleReservation;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VehicleReservationSearch implements Search<VehicleReservation>{

    private Map<Integer, VehicleReservation> vehicleReservations;
    private static VehicleReservationSearch vehicleReservationSearch;
    private VehicleReservationSearch(){
        vehicleReservations = new HashMap<>();
    }
    public static VehicleReservationSearch getVehicleReservationSearch(){
        if(vehicleReservationSearch == null)
            return new VehicleReservationSearch();
        else
            return vehicleReservationSearch;
    }

    @Override
    public VehicleReservation searchById(int id) {
        return vehicleReservations.get(id);
    }

    @Override
    public List<VehicleReservation> getAll() {
        return Collections.unmodifiableList(vehicleReservations.values().stream().collect(Collectors.toList()));
    }
}
