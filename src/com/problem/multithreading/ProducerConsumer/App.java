package com.problem.multithreading.ProducerConsumer;

import java.util.LinkedList;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        Processor p1 = new Processor();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    p1.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    p1.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
    }
}

class Processor{
    private LinkedList<Integer> list = new LinkedList<>();
    private static int LIMIT=10;
    private Random random = new Random(100);
    public void produce() throws InterruptedException {
        while(true){
            synchronized (this){
                if(list.size() == LIMIT){
                    wait();
                }
                list.add(random.nextInt());
                notify();
                Thread.sleep(100);
            }
        }

    }
    public void consume() throws InterruptedException {
        while(true){
            synchronized (this){
                if(list.size() == 0){
                    wait();
                }
                list.removeFirst();
                System.out.println("list size is" + list.size());
                notify();
                Thread.sleep(100);
            }
        }

    }
}
