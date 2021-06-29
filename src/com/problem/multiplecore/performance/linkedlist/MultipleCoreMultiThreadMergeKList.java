package com.problem.multiplecore.performance;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class MultipleCoreMultiThreadMergeKList {
    private int SEQUENTIAL_THRESHOLD;
    ListNode[] lists;

    MultipleCoreMultiThreadMergeKList(ListNode[] lists, int SEQUENTIAL_THRESHOLD){
        this.lists = lists;
        this.SEQUENTIAL_THRESHOLD = SEQUENTIAL_THRESHOLD;
    }

    public ListNode mergeKLists(ListNode[] lists){
        if(lists.length == 0) return null;

        MultiCoreMergeKList mc = new MultiCoreMergeKList( 0, lists.length-1);
        int  processor = Runtime.getRuntime().availableProcessors();
        ForkJoinPool pool = new ForkJoinPool(processor);
        return pool.invoke(mc);

    }

    private class MultiCoreMergeKList extends RecursiveTask<ListNode> {
        int start;
        int end;
        MultiCoreMergeKList(int start, int end){
            this.end = end;
            this.start = start;
        }
        @Override
        protected ListNode compute() {
            if(this.end - this.start <= SEQUENTIAL_THRESHOLD){
                return ListUtil.divideAndMerge(lists, this.start, this.end);
            } else {
                int middle = (this.start + this.end)/2;
                MultiCoreMergeKList firstTask = new MultiCoreMergeKList(start, middle);
                MultiCoreMergeKList secondTask = new MultiCoreMergeKList(middle+1, end);
                firstTask.fork();
                ListNode secondTaskListNode = secondTask.compute();
                return ListUtil.mergeTwoLists(secondTaskListNode,firstTask.join());
            }
        }
    }
}


