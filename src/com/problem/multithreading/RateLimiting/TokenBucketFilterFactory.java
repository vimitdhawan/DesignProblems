package com.problem.multithreading.RateLimiting;

public class TokenBucketFilterFactory {
    public static TokenBucketFilter createTokenFilter(int count, long time) {
        TokenBucketFilter filter = new TokenBucketFilter(count, time);

        Thread t1 = new Thread(() -> {
            filter.deamonThread();
        });
        t1.start();
        return filter;
    }
}
