package com.problem.renting.system.vehicle;

import com.problem.renting.system.constants.LogType;

import java.time.LocalDateTime;

public final class VehicleLog {
    private int id;
    private String description;
    private LogType type;
    private LocalDateTime creationDate;


    public VehicleLog(int id, String description, LogType type, LocalDateTime creationDate) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.creationDate = creationDate;
    }


}
