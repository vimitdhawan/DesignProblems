package com.problem.fliesystem;

import java.time.LocalDateTime;

public abstract class Entity {
    protected Directory parent;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdationDate;
    private LocalDateTime lastAccess;
    private String name;

    Entity(Directory parent, String name, String type){
        this.parent = parent;
        this.name = name;
        this.creationDate = LocalDateTime.now();
        this.lastAccess = LocalDateTime.now();
        this.lastUpdationDate = LocalDateTime.now();
    }

    public void rename(String name){
        this.name = name;
    }


}
