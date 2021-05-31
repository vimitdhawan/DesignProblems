package com.problem.multithreading.ProducerConsumer.educative;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerSynchronization {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueueSynchronization<Integer> blockingQueue = new BlockingQueueSynchronization<>(100);
        long startTime = System.currentTimeMillis();
        Thread producer1 = new Thread(() -> {
            for(int i =0; i<5000000; i++){

                try {
                    blockingQueue.enqueue(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        Thread producer2 = new Thread(() -> {
            for(int i =5000000; i<10000000; i++){

                try {
                    blockingQueue.enqueue(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        Thread consumer = new Thread(() -> {
            for(int i =0; i<10000000; i++){
                try {
                    blockingQueue.dequeue();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producer1.start();
        producer2.start();
        consumer.start();
        producer1.join();
        producer2.join();
        consumer.join();
        long endTime = System.currentTimeMillis();
        System.out.println("Total time taken by consumer producer " + (endTime - startTime));

    }
}


class BlockingQueueSynchronization<T>{
    private T[] queue;
    private int head;
    private int size;
    private int capacity;
    private int tail;
    private Lock lock;
    private Condition condition;


    BlockingQueueSynchronization(int capacity){
        this.queue = (T[]) new Object[capacity];
        this.capacity = capacity;
        this.lock = new ReentrantLock();
        this.condition = lock.newCondition();
    }

    public void enqueue(T item) throws InterruptedException {
        lock.lock();
        while(size == capacity){
            condition.await();
        }
        if(tail == capacity){
            tail =0;
        }
        queue[tail] = item;
        tail++;
        size++;
        condition.signalAll();
        lock.unlock();
    }

    public T dequeue() throws InterruptedException {
        lock.lock();
        while(size == 0){
            condition.await();
        }
        if(head == capacity){
            head =0;
        }
        T value = queue[head];
        head++;
        size++;
        condition.signalAll();
        lock.unlock();
        return value;
    }


}
