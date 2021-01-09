package com.problem.multithreading.RateLimiting;

public class TokenBucketFilter {
    private int maxSize;
    private long time;
    private int tokenCount;

    TokenBucketFilter(int maxSize, long time){
        this.maxSize = maxSize;
        this.time = time;
        this.tokenCount = maxSize;
    }

    public void deamonThread() {
        while(true){
            synchronized (this){
                if(tokenCount<maxSize){
                    tokenCount = maxSize;
                }
                this.notifyAll();
                try {
                    Thread.currentThread().sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    public void getToken(){
        synchronized (this){
            while(tokenCount==0){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            tokenCount--;
            System.out.println("request process at " + (System.currentTimeMillis()/1000));
        }



    }
}
