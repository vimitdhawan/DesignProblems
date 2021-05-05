package com.problem.renting.system.account;

import com.problem.renting.system.constants.AccountStatus;
import com.problem.renting.system.vehicle.Vehicle;
import com.problem.renting.system.vehicle.VehicleReservation;

public class Receptionist extends Account {
    public Receptionist(int accountId, String name, AccountStatus status) {
        super(accountId, name, status);
    }

    public Member searchMember(int memberId){
        return null;
    }

    public void addVehicle(Vehicle vehicle){
    }

    public VehicleReservation getReservation(){
        return null;
    }
}
