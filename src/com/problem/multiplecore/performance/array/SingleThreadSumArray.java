package com.problem.multiplecore.performance.array;

public class SingleThreadMergeKList {

    public ListNode mergeKLists(ListNode[] lists){
        if(lists.length == 0) return null;
        return ArrayUtil.divideAndMerge(lists, 0, lists.length-1);

    }

}
