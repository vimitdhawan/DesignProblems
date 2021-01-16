package com.problem.multithreading.numberseries;

public class App {
    public static void main(String[] args) throws InterruptedException {
        NumberSeries numberSeries = new NumberSeries(100);
        Thread t1 = new Thread(() -> {
            try {
                numberSeries.printOdd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                numberSeries.printEven();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Number series finish");
    }


}

class NumberSeries {
    int current = 1;
    int number;
    NumberSeries(int number){
        this.number = number;
    }

    public void printOdd() throws InterruptedException {
        synchronized (this){
            while(current<=number){
                    while(current%2==0){
                        wait();
                    }
                if(current<=number)System.out.println(current);
                    current++;
                    notifyAll();
                }
            }

    }

    public void printEven() throws InterruptedException {
        synchronized (this){
            while(current<=number){
                while(current%2!=0){
                    wait();
                }
                if(current<=number)System.out.println(current);
                current++;
                notifyAll();
            }

        }
    }


}
