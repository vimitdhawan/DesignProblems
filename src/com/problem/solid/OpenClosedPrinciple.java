package com.problem.solid;

public class OpenClosedPrinciple {


    abstract class Vehicle {
        public abstract double calculateValue();
    }
    class Car extends Vehicle {
        public double calculateValue() {
            return 2.5d;
        }
    }
    class Bike extends Vehicle {
        public double calculateValue() {
            return 4.5d;
        }
    }


    // wrong way to do

    class BadOpenClosedPrinciple {


        public double calculateValue(Vehicle v) {
            if (v instanceof Car) {
                return v.calculateValue() * 0.8;
            }
            if (v instanceof Bike) {
                return v.calculateValue() * 0.5;

            }
            return 0d;

        }


    }


}


