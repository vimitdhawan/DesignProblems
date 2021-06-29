package com.problem.multiplecore.performance.array;

import java.util.Arrays;

public interface ArrayUtil {

    static int[] createArray(int noOfElement){
        int[] arr = new int[noOfElement];
        Arrays.fill(arr, 1);
        return arr;
    }

    static int sumArray(int[] arr, int start, int end) {
        int sum =0;
       while(start<=end){
           sum += arr[start];
           start++;
       }
        return sum;
    }



}


