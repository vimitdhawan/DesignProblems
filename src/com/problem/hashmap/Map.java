package com.problem.hashmap;

import java.util.Optional;

public interface Map<K, V> {
     Optional<V> get(K key);
     Optional<V>  put(K key, V value);
     boolean delete(K key);
}