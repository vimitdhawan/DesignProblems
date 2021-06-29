package com.problem.multiplecore.performance.linkedlist;

import java.util.concurrent.ExecutionException;

public class App {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ListNode[] list = ListUtil.createLists(100000000, 1);
        SingleThreadMergeKList st = new SingleThreadMergeKList();
        long startTime = System.currentTimeMillis();
        ListNode res = st.mergeKLists(list);
        long finishTime = System.currentTimeMillis();
        System.out.println("Single thread merge k lists in " + (finishTime - startTime) + " milli seconds") ;

        MultipleThreadMergeKList mt = new MultipleThreadMergeKList();
        ListNode[] mtList = ListUtil.createLists(100000000, 1);
        System.out.println("-----------------------------------------------------");
        long mThreadStartTime =  System.currentTimeMillis();
        mt.mergeKLists(mtList);
        long mThreadFinishTime = System.currentTimeMillis();
        System.out.println("Multiple thread merge k lists in " + (mThreadFinishTime - mThreadStartTime) + " milli seconds");

        System.out.println("-----------------------------------------------------");

        ListNode[] mcList = ListUtil.createLists(100000000, 1);
        int SEQUENTIAL_THRESHOLD = 1000;
        MultipleCoreMultiThreadMergeKList mc = new MultipleCoreMultiThreadMergeKList(mcList, SEQUENTIAL_THRESHOLD);
        long mcThreadStartTime =  System.currentTimeMillis();
        mc.mergeKLists(mcList);
        long mcThreadFinishTime = System.currentTimeMillis();
        System.out.println("Multi core thread thread merge k lists in " + (mcThreadFinishTime - mcThreadStartTime) + " milli seconds") ;

    }
}
