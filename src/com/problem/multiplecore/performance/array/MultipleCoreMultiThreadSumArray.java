package com.problem.multiplecore.performance.array;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class MultipleCoreMultiThreadSumArray {
    private int SEQUENTIAL_THRESHOLD;
    int[] array;

    MultipleCoreMultiThreadSumArray(int[] array, int SEQUENTIAL_THRESHOLD){
        this.array = array;
        this.SEQUENTIAL_THRESHOLD = SEQUENTIAL_THRESHOLD;
    }

    public int sumArray(int[] input){
        if(input.length == 0) return 0;

        SingleTask mc = new SingleTask( 0, input.length-1);
        int  processor = Runtime.getRuntime().availableProcessors();
        ForkJoinPool pool = new ForkJoinPool(processor);
        return pool.invoke(mc);

    }

    private class SingleTask extends RecursiveTask<Integer> {
        int start;
        int end;

        SingleTask( int start, int end){
            this.end = end;
            this.start = start;
        }
        @Override
        protected Integer compute() {
            if(this.end - this.start <= SEQUENTIAL_THRESHOLD){
                return ArrayUtil.sumArray(array, this.start, this.end);
            } else {
                int middle = (this.start + this.end)/2;
                SingleTask firstTask = new SingleTask(start, middle);
                SingleTask secondTask = new SingleTask(middle+1, end);
                firstTask.fork();
                int sum = secondTask.compute();
                return sum + firstTask.join();
            }
        }
    }
}


