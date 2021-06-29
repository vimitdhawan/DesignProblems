package com.problem.multiplecore.performance.array;

import java.util.concurrent.ExecutionException;

public class App {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int noOfElement = 200000000;
        int noOfThread = 10;
        int SEQUENTIAL_THRESHOLD = 10000000;
        int[] array = ArrayUtil.createArray(noOfElement);


        SingleThreadSumArray st = new SingleThreadSumArray();
        long startTime = System.currentTimeMillis();
        int res = st.sumArray(array);
        long finishTime = System.currentTimeMillis();
        System.out.println("Single thread added " + noOfElement + " 1 in " + (finishTime - startTime) + " milli seconds " + res);

        MultipleThreadSumArray mt = new MultipleThreadSumArray();
        System.out.println("-----------------------------------------------------");
        long mThreadStartTime =  System.currentTimeMillis();
        int mRes = mt.sumArray(array, noOfThread);
        long mThreadFinishTime = System.currentTimeMillis();
        System.out.println(noOfThread + " thread added " + noOfElement + " 1 in " + (mThreadFinishTime - mThreadStartTime) + " milli seconds with res " + mRes);

        System.out.println("-----------------------------------------------------");

        MultipleCoreMultiThreadSumArray mc = new MultipleCoreMultiThreadSumArray(array, SEQUENTIAL_THRESHOLD);
        long mcThreadStartTime =  System.currentTimeMillis();
        int mcRes = mc.sumArray(array);
        long mcThreadFinishTime = System.currentTimeMillis();
        System.out.println( "Multicore added " + noOfElement + " 1 in with sequential threshold " + SEQUENTIAL_THRESHOLD+ " in time " + (mcThreadFinishTime - mcThreadStartTime) + " milli seconds with res " + mcRes);

    }
}
