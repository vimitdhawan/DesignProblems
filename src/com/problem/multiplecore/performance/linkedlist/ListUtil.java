package com.problem.multiplecore.performance;

public interface ListUtil {

    static ListNode[] createLists(int noOfList, int elementPerList){
        int currentElement = Integer.MIN_VALUE;
        ListNode[] lists = new ListNode[noOfList];
        while(noOfList>0){
            ListNode prevHead = new ListNode(0);
            ListNode traverse = prevHead;
            int noOfElements = elementPerList;
            while(noOfElements>0){
                traverse.next = new ListNode(currentElement);
                currentElement++;
                traverse = traverse.next;
                noOfElements--;
            }
            noOfList--;
            lists[noOfList] = prevHead.next;
        }
        return lists;
    }

    static ListNode divideAndMerge(ListNode[] lists, int start, int end){
        if(start>end) return null;
        if(start == end) return lists[start];
        if(end - start == 1) return mergeTwoLists(lists[start], lists[end]);
        else  {
            int middle = (start + end)/2;
            return mergeTwoLists(divideAndMerge(lists, start, middle), divideAndMerge(lists, middle + 1, end));
        }

    }

    static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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


