package com.problem.multithreading.RateLimiting;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executors = Executors.newFixedThreadPool(5);
        TokenBucketFilter tokenBucketFilter = TokenBucketFilterFactory.createTokenFilter(20, 10000);
        for(int i =0; i<1000; i++){
            executors.submit(() -> {
                tokenBucketFilter.getToken();
            });
        }
        executors.shutdown();
        executors.awaitTermination(1, TimeUnit.DAYS);
    }
}
