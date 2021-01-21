package com.problem.cache;

import java.util.*;

interface LRU<K, V>{
     V get(K key);
     void set(K key, V value);

}

public class LRUCache<K, V> implements LRU<K, V>{
    private Node<K, V> head;
    private Node <K, V> tail;
    private Map<K, Node<K, V>> map;
    private int size;
    public LRUCache(int cap)
    {
        map = new HashMap<>(size);
        this.size = cap;

    }

    public V get(K key)
    {
        if(map.containsKey(key)){
            Node<K, V>  node = map.get(key);
            removeNode(node);
            moveNodeAtTop(node);
            return map.get(key).value;
        }
        return null;


    }

    public void set(K key, V value)
    {
        Node<K, V> node = new Node<>(key, value);
        removeKey(key);
        if(map.size()>= size){
            removeKey(tail.key);
        }
        moveNodeAtTop(node);
        map.put(key, node);

    }

    private void removeKey(K key){
        Node<K, V> temp =  map.get(key);
        removeNode(temp);
        map.remove(key);
    }

    private void removeNode(Node<K, V> node){
        if(node  == null) return;
        if(node.previous!= null){
            node.previous.next = node.next;

        }
        if(node.next!= null){
            node.next.previous = node.previous;
        }
        if(node == tail){
            tail = node.previous;
        }
        if(node == head){
            head = node.next;
        }
    }

    private void moveNodeAtTop(Node<K, V>  node){
        if(head == null){
            head = node;
            tail = node;
        } else {
            node.next = head;
            head.previous = node;
            head = node;
        }

    }

    private static class Node<K, V> {
        Node<K, V> next;
        Node<K, V> previous;
        K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }


}




