package com.problem.multithreading.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreApp {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor =  Executors.newCachedThreadPool();
        Connection connection = Connection.getConnection();
        for(int i=0; i<100; i++){
            executor.submit(() -> {
                try {
                    connection.connect();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
        executor.awaitTermination(1000, TimeUnit.MILLISECONDS);
    }


}

class Connection {

    private static Connection conn = new Connection();
    private int counter;
    private Semaphore semaphore = new Semaphore(10);

    public static Connection getConnection(){
        return conn;
    }

    public void connect() throws InterruptedException {
        semaphore.acquire();
        synchronized (this){
            counter++;
            System.out.println("Connect with " + counter);
        }

        Thread.sleep(1000);
        synchronized (this){
            counter--;
        }
        semaphore.release();

    }


}
