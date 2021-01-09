package com.problem.immutable;

public final class Immutable {
    private final int[] arr;
    public Immutable(int[] arr){
        this.arr = arr.clone();
    }

    public int[] getArr(){
        return arr.clone();
    }

}
