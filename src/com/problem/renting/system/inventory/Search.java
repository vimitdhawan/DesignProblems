package com.problem.renting.system.inventory;

import com.problem.renting.system.vehicle.Vehicle;

import java.util.List;

public interface Search<T> {
    T searchById(int id);
    List<T> getAll();
}
