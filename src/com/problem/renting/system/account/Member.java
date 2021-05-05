package com.problem.renting.system.account;

import com.problem.renting.system.constants.AccountStatus;
import com.problem.renting.system.inventory.VehicleReservationSearch;
import com.problem.renting.system.vehicle.VehicleReservation;

import java.time.LocalDateTime;

public class Member extends Account{

    private int drivingLicenceId;
    private LocalDateTime issuedDate;

    public Member(int accountId, String name, AccountStatus status, int drivingLicenceId, LocalDateTime issuedDate) {
        super(accountId, name, status);
        this.drivingLicenceId = drivingLicenceId;
        this.issuedDate = issuedDate;
    }

    public VehicleReservation getReservation(int reservationId){
        return VehicleReservationSearch.getVehicleReservationSearch().searchById(reservationId);
    }

}
