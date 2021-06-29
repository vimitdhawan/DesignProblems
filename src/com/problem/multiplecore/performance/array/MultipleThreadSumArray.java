package com.problem.multiplecore.performance.array;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MultipleThreadSumArray {

    public int sumArray(int[] input, int noOfThreads) throws InterruptedException {
        if(input.length == 0) return 0;
        List<Future<Integer>> pool = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(noOfThreads);
        int divider = input.length/noOfThreads;
        for(int i =0; i<noOfThreads; i++){
            final int start = (i * divider);
            final int end = start + divider - 1;
            pool.add(executor.submit(() -> ArrayUtil.sumArray(input, start, end))) ;
        }
        int finalSum = pool.parallelStream().mapToInt(f -> {
            int sum =0;
            try {
                sum= f.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return sum;
        }).sum();
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
        return finalSum;
    }

}
