package com.problem.multithreading.deadlock;

import java.util.concurrent.locks.ReentrantLock;

// If this program did not terminate in 10 sec its deadloak
public class App {
    public static void main(String[] args) throws InterruptedException {
        ThreadRunner tr = new ThreadRunner();
        Thread t1 = new Thread(() -> {
            try {
                tr.firstThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        Thread t2 = new Thread(() -> {

            try {
                tr.secondThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.start();
        t1.join();
        t2.join();
        tr.finalAmount();
    }
}

class ThreadRunner{
    private Account account1;
    private Account account2;
    private ReentrantLock lock1 = new ReentrantLock();
    private ReentrantLock lock2 = new ReentrantLock();
    ThreadRunner() {
        account1 = new Account(2000);
        account2 = new Account(2000);
    }
    public void firstThread() throws InterruptedException {
             for(int i=0; i<1000; i++) {
                 lock2.lock();
                 lock1.lock();
                 Account.transfer(account1, account2, i);
                 lock1.unlock();
                 lock2.unlock();
                 Thread.sleep(10);
             }

    }
    public void secondThread() throws InterruptedException {
            for(int i=0; i<1000; i++) {
                lock1.lock();
                lock2.lock();
                Account.transfer(account2,account1, i);
                lock1.unlock();
                lock2.unlock();
                Thread.sleep(10);
            }

    }
    public void finalAmount(){
        System.out.println(account1.amount + account2.amount);
    }
}





class Account {
    int amount;
    public Account(int amount){
        this.amount = amount;
    }

    public void withdraw(int amount){
        this.amount -= amount;
    }

    public void deposit(int amount){
        this.amount += amount;
    }

    public static void transfer(Account acc1, Account acc2, int amount){
        acc1.withdraw(amount);
        acc2.deposit(amount);
    }

}
