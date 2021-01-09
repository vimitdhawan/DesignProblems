package com.problem.multithreading.types;

public class RunnableType {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Test());
        t1.start();
    }
}

class Test implements Runnable {
    @Override
    public void run() {
        for(int i =0; i<100; i++){
            System.out.println(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}