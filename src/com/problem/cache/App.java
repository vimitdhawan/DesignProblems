package com.problem.cache;

public class App {
    public static void main(String[] args) {
        LRU<Integer, String> cache = new LRUCache<>(5);
        for(int i =0; i< 5; i++){
            cache.set(i, "vimit"+i);
        }
        System.out.println(cache.get(5));
        System.out.println(cache.get(4));
        System.out.println(cache.get(2));
        cache.set(9, "rohan");
        System.out.println(cache.get(9));
        System.out.println(cache.get(0));

    }
}
