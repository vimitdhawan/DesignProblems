package com.problem.renting.system.inventory;


import com.problem.renting.system.vehicle.Vehicle;

import java.util.*;
import java.util.stream.Collectors;

public class VehicleSearch implements Search<Vehicle>{

    private Map<Integer, Vehicle> vehicles;
    private static VehicleSearch vehicleSearch;
    private VehicleSearch(){
        vehicles = new HashMap<>();
    }
    public static VehicleSearch getVehicleSearch(){
        if(vehicleSearch == null)
            return new VehicleSearch();
        else
            return vehicleSearch;
    }

    @Override
    public Vehicle searchById(int id) {
        return vehicles.get(id);
    }

    @Override
    public List<Vehicle> getAll() {
        return Collections.unmodifiableList(vehicles.values().stream().collect(Collectors.toList()));
    }
}