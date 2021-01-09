package com.problem.multithreading.synchronization;

public class App {
    private volatile int count = 0;
    public static void main(String[] args) throws InterruptedException {
        App app = new App();
        app.doWork();
        System.out.println(app.count);
    }

    private void doWork() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<100; i++){
                    incriment();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<100; i++){
                    incriment();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    private synchronized void incriment(){
        count++;
    }
}
