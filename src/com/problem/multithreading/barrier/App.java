package com.problem.multithreading.barrier;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws InterruptedException {
        Barrier barrier = new Barrier(5);
        ExecutorService executor1 = Executors.newFixedThreadPool(3);
        ExecutorService executor2 = Executors.newFixedThreadPool(3);
        for(int i =0; i<5; i++){
            final int k =i;
            executor1.submit(() -> {
                try {
                    Thread.currentThread().setName("Thread Pool 1 "+  k);
                    barrier.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        for(int i =0; i<5; i++){
            final int k =i;
            executor2.submit(() -> {
                try {
                    Thread.currentThread().setName("Thread Pool 2 "+  k);
                    barrier.acquire();
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executor1.shutdown();
        executor1.awaitTermination(10000, TimeUnit.SECONDS);
        executor2.shutdown();
        executor2.awaitTermination(10000, TimeUnit.SECONDS);
        System.out.println("Barrier shutdown");

    }
}

class Barrier {
    private int counter;
    private int totalThreads;
    private int released;
    Barrier(int totalThreads) {
        this.totalThreads = totalThreads;
    }

    public void acquire() throws InterruptedException {
        synchronized (this){
            while(counter == totalThreads){
                wait();
            }
            counter++;
            if(counter == totalThreads){
                released= totalThreads;
                this.notifyAll();
            } else{
                while(counter<totalThreads){
                    System.out.println("QueueThread "+ Thread.currentThread().getName());
                    this.wait();
                }
            }
            System.out.println("ProcessingThread "+ Thread.currentThread().getName());
            released--;
            if(released == 0){
                counter = 0;
                this.notifyAll();
            }
        }
    }

}
