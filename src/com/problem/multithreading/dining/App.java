package com.problem.multithreading.dining;

import java.util.concurrent.Semaphore;

public class App {
    public static void main(String[] args) throws InterruptedException {
        DiningPhilosopherProblem dp = new DiningPhilosopherProblem();
        Thread t1 = new Thread(() -> {
            for(int i =0; i<5; i++){
                try {
                    dp.philosopherEating(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            for(int i =0; i<5; i++){
                try {
                    dp.philosopherEating(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t3 = new Thread(() -> {
            for(int i =0; i<5; i++){
                try {
                    dp.philosopherEating(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });    Thread t4 = new Thread(() -> {
            for(int i =0; i<5; i++){
                try {
                    dp.philosopherEating(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t5 = new Thread(() -> {
            for(int i =0; i<5; i++){
                try {
                    dp.philosopherEating(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        System.out.println("Everyone finish Dinner");

    }
}

class DiningPhilosopherProblem {
    Semaphore[] spoon = new Semaphore[5];
    Semaphore philosopher = new Semaphore(4);

    DiningPhilosopherProblem() {
        spoon[0] = new Semaphore(1);
        spoon[1] = new Semaphore(1);
        spoon[2] = new Semaphore(1);
        spoon[3] = new Semaphore(1);
        spoon[4] = new Semaphore(1);
    }
    public void philosopherEating(int id) throws InterruptedException {
        philosopher.acquire();
        spoonAcquire(id);
        System.out.println("philosopher start chunk " + id);
        Thread.sleep(2000);
        System.out.println("philosopher1 finished chunk " +id);
        spoonRelease(id);
        philosopher.release();
    }

    public void spoonAcquire(int id) throws InterruptedException {
        spoon[id].acquire();
        spoon[(id+1)%5].acquire();
    }
    public void spoonRelease(int id) throws InterruptedException {
        spoon[id].release();
        spoon[(id+1)%5].release();
    }

}