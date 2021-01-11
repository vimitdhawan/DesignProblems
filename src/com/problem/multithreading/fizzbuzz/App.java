package com.problem.multithreading.fizzbuzz;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws InterruptedException {
        FizzBuzz fizzbuzz = new FizzBuzz(30);
        ExecutorService executor = Executors.newFixedThreadPool(4);
        for(int i=0; i<4; i++) {
            final int k = i;
            executor.submit(() -> {
                if (k == 0) {
                    try {
                        fizzbuzz.number();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (k == 1) {
                    try {
                        fizzbuzz.fizz();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (k == 2) {
                    try {
                        fizzbuzz.buzz();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (k == 3) {
                    try {
                        fizzbuzz.fizzbuzz();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            });
        }
            executor.shutdown();
            executor.awaitTermination(10000, TimeUnit.SECONDS);
            System.out.println("Fizz Buzz Over");


    }
}

class FizzBuzz {
    int number;
    int currentNumber =1;
    FizzBuzz(int number){
        this.number = number;
    }

    public synchronized void fizz() throws InterruptedException {
        while(currentNumber<=number){
            if(currentNumber % 3 ==0 && currentNumber % 5 != 0){
                System.out.println("Fizz");
                currentNumber++;
                notifyAll();
            }else{
                wait();
            }
        }
    }
    public synchronized void buzz() throws InterruptedException {
        while(currentNumber<=number){
            if(currentNumber % 5 ==0 && currentNumber % 3 != 0){
                System.out.println("Buzz");
                currentNumber++;
                notifyAll();
            }else{
                wait();
            }
        }

    }
    public synchronized void fizzbuzz() throws InterruptedException {
        while(currentNumber<=number){
            if(currentNumber % 15 == 0){
                System.out.println("Fizz-Buzz");
                currentNumber++;
                notifyAll();
            }else{
                wait();
            }
        }
    }
    public synchronized void number() throws InterruptedException {
        while(currentNumber<=number){
            if(currentNumber % 5 !=0 && currentNumber % 3 != 0){
                System.out.println(currentNumber);
                currentNumber++;
                notifyAll();
            }else{
                wait();
            }
        }

    }
}
