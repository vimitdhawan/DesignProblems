package com.problem.hashmap;

import java.util.ArrayList;
import java.util.List;

public interface Map<K, V> {
     V get(K key);
     void put(K key, V value);
     boolean delete(K key);
}