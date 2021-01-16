package com.problem.multithreading.dining;

import java.util.concurrent.Semaphore;

public class App {
    public static void main(String[] args) throws InterruptedException {
        DiningPhilosopherProblem dp = new DiningPhilosopherProblem();
        Thread t1 = new Thread(() -> {
            for(int i =0; i<5; i++){
                try {
                    dp.philosopher1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            for(int i =0; i<5; i++){
                try {
                    dp.philosopher2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t3 = new Thread(() -> {
            for(int i =0; i<5; i++){
                try {
                    dp.philosopher3();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });    Thread t4 = new Thread(() -> {
            for(int i =0; i<5; i++){
                try {
                    dp.philosopher4();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t5 = new Thread(() -> {
            for(int i =0; i<5; i++){
                try {
                    dp.philosopher5();
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
    Semaphore spoon = new Semaphore(5);
    Semaphore philosopher = new Semaphore(4);

    public void philosopher1() throws InterruptedException {
        philosopher.acquire();
        spoonAcquire();
        System.out.println("philosopher1 start chunk");
        Thread.sleep(2000);
        System.out.println("philosopher1 finished chunk");
        spoonRelease();
        philosopher.release();
    }
    public void philosopher2() throws InterruptedException {
        philosopher.acquire();
        spoonAcquire();
        System.out.println("philosopher2 start chunk");
        Thread.sleep(2000);
        System.out.println("philosopher2 finished chunk");
        spoonRelease();
        philosopher.release();
    }
    public void philosopher3() throws InterruptedException {
        philosopher.acquire();
        spoonAcquire();
        System.out.println("philosopher3 start chunk");
        Thread.sleep(2000);
        System.out.println("philosopher3 finished chunk");
        spoonRelease();
        philosopher.release();
    }
    public void philosopher4() throws InterruptedException {
        philosopher.acquire();
        spoonAcquire();
        System.out.println("philosopher4 start chunk");
        Thread.sleep(2000);
        System.out.println("philosopher4 finished chunk");
        spoonRelease();
        philosopher.release();
    }
    public void philosopher5() throws InterruptedException {
        philosopher.acquire();
        spoonAcquire();
        System.out.println("philosopher5 start chunk");
        Thread.sleep(2000);
        System.out.println("philosopher5 finished chunk");
        spoonRelease();
        philosopher.release();
    }

    public void spoonAcquire() throws InterruptedException {
        spoon.acquire();
        spoon.acquire();
    }
    public void spoonRelease() throws InterruptedException {
        spoon.release();
        spoon.release();
    }

}