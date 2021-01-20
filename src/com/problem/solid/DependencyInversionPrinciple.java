package com.problem.solid;

// class should not depend upon concrete implimentation
public class DependencyInversionPrinciple {

    interface Engine {
        void start();
    }

    class DieselEngine implements Engine{

        @Override
        public void start() {

        }
    }

    class GoodVehicle{
        DieselEngine engine;
        public void start(){
            engine.start();
        }
    }




    // Bad implimentation
    class BadEngine{
        public void start(){}
    }

    class  Vehicle{
        BadEngine engine;
        public void start(){
            engine.start();
        }
    }



}
