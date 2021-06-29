package com.problem.multiplecore.performance.array;

public class SingleThreadSumArray {

    public int sumArray(int[] input){
        if(input.length == 0) return 0;
        return ArrayUtil.sumArray(input, 0, input.length-1);

    }

}
