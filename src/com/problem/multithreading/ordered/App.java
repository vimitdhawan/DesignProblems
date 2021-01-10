package com.problem.multithreading.ordered;

public class App {
    public static void main(String[] args) throws InterruptedException {
        OrderedPrinting op = new OrderedPrinting();
        Thread t1 = new Thread(() -> {
            for(int i =0; i<5; i++){
                try {
                    op.printFirst();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        Thread t2 = new Thread(() -> {
            for(int i =0; i<5; i++){
                try {
                    op.printSecond();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        Thread t3 = new Thread(() -> {
            for(int i =0; i<5; i++){
                try {
                    op.printThird();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        t3.start();
        Thread.sleep(100);
        t2.start();
        t1.start();
        t1.join();
        t2.join();
        t3.join();
    }
}

class OrderedPrinting{
    private boolean isFirst = false;
    private boolean isSecond = false;

    public synchronized void printFirst() throws InterruptedException {
        while(isFirst){
            this.wait();
        }
        System.out.println("first");
        isFirst = true;
        this.notifyAll();
    }
    public synchronized void printSecond() throws InterruptedException {
        while(!isFirst || isSecond){
            this.wait();
        }
        isSecond = true;
        System.out.println("second");
        this.notifyAll();
    }
    public synchronized void printThird() throws InterruptedException {
        while(!isFirst || !isSecond){
            this.wait();
        }
        isFirst = false;
        isSecond = false;
        System.out.println("third");
        this.notifyAll();
    }
}