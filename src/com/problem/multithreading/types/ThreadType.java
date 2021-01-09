package com.problem.multithreading.types;

public class ThreadType {
    public static void main(String[] args) {
        Thread t1 = new Test1();
        t1.start();
    }
}

class Test1 extends Thread {
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
