package com.problem.multithreading.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        for(int i=0; i<5; i++ ){
            executor.submit(new Processor(i+1));
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("Main Thread Finish");
    }
}

class Processor implements Runnable{
    int id;
    Processor(int id){
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Processor"+ id + "started");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Processor"+ id + "finished");
    }
}
