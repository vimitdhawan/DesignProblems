package com.problem.solid;

public class InterfaceSegregationPrinciple {

    interface Vehicle {
         void drive();
         void stop();
         void refuel();
         void openDoors();
    }
    class Bike implements Vehicle {

        // Can be implemented
        public void drive() {}
        public void stop() {}
        public void refuel() {}

        // Can not be implemented
        public void openDoors() {}
    }
}
