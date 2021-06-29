package com.problem.multiplecore.performance;

import java.util.concurrent.*;

public class MultipleThreadMergeKList {

    public ListNode mergeKLists(ListNode[] lists) throws InterruptedException, ExecutionException {
        if(lists.length == 0) return null;
        ExecutorService executor = Executors.newFixedThreadPool(2);
        int middle = lists.length/2;
        Future<ListNode> listNode1 = executor.submit(() -> ListUtil.divideAndMerge(lists, 0, middle));
        Future<ListNode> listNode2 = executor.submit(() -> ListUtil.divideAndMerge(lists, middle+1, lists.length-1));
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
        return ListUtil.mergeTwoLists(listNode1.get(), listNode2.get());
    }

}
