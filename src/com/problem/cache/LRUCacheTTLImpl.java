package com.problem.cache;

import java.time.LocalDateTime;
import java.util.PriorityQueue;
import java.util.*;

interface LRUCacheTTL{
    Optional<Integer> get(int key);
    void set(int key, int value, int  seconds);
}

public class LRUCacheTTLImpl implements LRUCacheTTL{
    private PriorityQueue<ValueActive> valueActives;
    private Map<Integer, ValueActive> values;
    private int currentSize;
    private int size;
    LRUCacheTTLImpl(int size){
        valueActives = new PriorityQueue<>(Comparator.comparingInt(ttl -> ttl.dateTime.getSecond()));
        values = new HashMap<>();
        this.size = size;
        this.currentSize = 0;
    }

    @Override
    public Optional<Integer> get(int key) {
        if(values.containsKey(key) && values.get(key).dateTime.isAfter(LocalDateTime.now())){
            return Optional.of(values.get(key).value);
        }
        return Optional.of(null);
    }

    @Override
    public void set(int key, int value, int seconds) {
        if(size == currentSize){
            valueActives.poll();
            currentSize--;
        }
        ValueActive valueActive = new ValueActive(value, seconds);
        valueActives.add(valueActive);
        values.put(key, valueActive);
        currentSize++;

    }
}

class ValueActive {
    int value;
    LocalDateTime dateTime;
    ValueActive(int value, int seconds){
        this.value = value;
        this.dateTime = LocalDateTime.now().plusSeconds(seconds);
    }
}



