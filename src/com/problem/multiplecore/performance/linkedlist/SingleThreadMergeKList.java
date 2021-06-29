package com.problem.multiplecore.performance;

public class SingleThreadMergeKList {

    public ListNode mergeKLists(ListNode[] lists){
        if(lists.length == 0) return null;
        return ListUtil.divideAndMerge(lists, 0, lists.length-1);

    }

}
