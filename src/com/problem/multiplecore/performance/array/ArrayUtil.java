package com.problem.multiplecore.performance.array;

import java.util.Arrays;

public interface ListUtil {

    static int[] createArray(int noOfElement){
        int[] arr = new int[noOfElement];
        Arrays.fill(arr, 1);
        return arr;
    }

    static int sumArray(int[] arr, int start, int end) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode tempNode = new ListNode(0);
        ListNode traverseNode = tempNode;
        while (l1!=null&&l2!=null) {
            if (l1.val>l2.val) {
                traverseNode.next = l2;
                traverseNode = traverseNode.next;
                l2 = l2.next;
            } else {
                traverseNode.next = l1;
                traverseNode = traverseNode.next;
                l1 = l1.next;
            }
        }
        if (l2!=null) l1=l2;
        traverseNode.next=l1;
        return tempNode.next;

    }



}


