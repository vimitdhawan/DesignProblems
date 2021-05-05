package com.problem.renting.system.vehicle;

import com.problem.renting.system.constants.PaymentStatus;
import com.problem.renting.system.constants.ReservationStatus;

import java.time.LocalDateTime;

public class VehicleReservation {
    String reservationId;
    LocalDateTime creation;
    ReservationStatus status;
    LocalDateTime pickupDate;
    PaymentStatus paymentStatus;
    LocalDateTime returnDate;
    Vehicle vehicle;

    public VehicleReservation(String reservationId, LocalDateTime creation, ReservationStatus status, LocalDateTime pickupDate, PaymentStatus paymentStatus, LocalDateTime returnDate, Vehicle vehicle) {
        this.reservationId = reservationId;
        this.creation = creation;
        this.status = status;
        this.pickupDate = pickupDate;
        this.paymentStatus = paymentStatus;
        this.returnDate = returnDate;
        this.vehicle = vehicle;
    }


}
