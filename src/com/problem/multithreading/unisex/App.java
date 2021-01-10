package com.problem.multithreading.unisex;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws InterruptedException {
        Bathroom bathroom = new Bathroom();
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for(int i =0; i<5; i++){
            executor.submit(() -> {
                try {
                    bathroom.acquireMen();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        for(int i =0; i<5; i++){
            executor.submit(() -> {
                try {
                    bathroom.acquireWomen();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        for(int i =0; i<5; i++){
            executor.submit(() -> {
                try {
                    bathroom.acquireMen();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        for(int i =0; i<5; i++){
            executor.submit(() -> {
                try {
                    bathroom.acquireWomen();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("Bathroom shutdown");
    }
}

 class Bathroom {
    int menCounter = 0;
    int womenCounter = 0;
    public  void acquireMen() throws InterruptedException {
        synchronized (this){
            while(womenCounter!=0 || menCounter>=3){
                this.wait();
            }
            menCounter++;
            System.out.println("Men enter in bathroom total Men Count " + menCounter + " women Count " + womenCounter);
        }
        Thread.sleep(3000);
        synchronized (this){
            menCounter--;
            System.out.println("Men leaving from bathroom total Men Count " + menCounter + " women Count " + womenCounter);
            notifyAll();
        }


    }
    public void acquireWomen() throws InterruptedException {
        synchronized (this){
            while(menCounter!=0 || womenCounter>=3){
                this.wait();
            }
            womenCounter++;
            System.out.println("Women enter in bathroom total Women Count " + womenCounter + " men Count " + menCounter);
        }

        Thread.sleep(2000);
        synchronized (this){
            womenCounter--;
            System.out.println("Women leaving from bathroom total Women Count " + womenCounter + " men Count " + menCounter);
            notifyAll();
        }
    }

}
