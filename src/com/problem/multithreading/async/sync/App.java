package com.problem.multithreading.async.sync;

public class App {
    public static void main(String[] args) {
        AsyncExecutor executor = new AsyncExecutor();
        executor.asyncExecution(() -> {
            System.out.println("Async Callback Done");
        });
        System.out.println("Async Main Thread Finish");
       /* SynchronusExecutor synchronusExecutor = new SynchronusExecutor();
        synchronusExecutor.asyncExecution(() -> {
            System.out.println("Sync Callback Done");
        });
        System.out.println("Sync Main Thread Finish");*/
    }
}

interface Callback{
    void done();
}

class SynchronusExecutor extends AsyncExecutor{

    @Override
    public void asyncExecution(Callback callback) {
        final Object signal = new Object();
        final boolean[] isDone = new boolean[1];
        super.asyncExecution(() -> {
            callback.done();
            synchronized (signal){
                isDone[0] = true;
                signal.notifyAll();
            }
        });
        synchronized(signal){
            while(!isDone[0]){
                try {
                    signal.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}

class AsyncExecutor{
    public void asyncExecution(Callback callback){
        Thread t1 = new Thread(()-> {
            System.out.println("I am chilling");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            callback.done();
        });
        t1.start();
    }
}