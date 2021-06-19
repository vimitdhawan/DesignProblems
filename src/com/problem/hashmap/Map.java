package com.problem.hashmap;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface Map<K, V> {
     Optional<V> get(K key);
     void put(K key, V value);
     boolean delete(K key);
}