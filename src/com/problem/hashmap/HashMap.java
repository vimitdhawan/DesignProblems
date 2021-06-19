package com.problem.hashmap;

import jdk.nashorn.internal.codegen.OptimisticTypesPersistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HashMap<K, V> implements Map<K, V> {
    int capacity;
    private List<Optional<ListNode<K, V>>> list;

    public HashMap(int capacity){
        this.capacity = capacity;
        list = new ArrayList<>(capacity);
        for(int i =0; i< capacity; i++){
            list.add(Optional.empty());
        }
    }

    @Override
    public Optional<V> get(K key) {
        Optional<ListNode<K, V>> node = getNode(key);
        if(node.isPresent()) {
            return Optional.of(node.get().value);
        }
        return Optional.empty();
    }

    @Override
    public void put(K key, V value) {
        Optional<ListNode<K, V>> existingNode = getNode(key);
        if(existingNode.isPresent()){
            existingNode.get().value = value;
            return;
        }
        int index = getIndex(key);
        Optional<ListNode<K, V>> node = list.get(index);
        Optional<ListNode<K, V>> tempNode = Optional.of(new ListNode<>(key, value));
        if(node.isPresent()){
            tempNode.get().next = node;
            node.get().previous = tempNode;
        }
        list.add(index, tempNode);

    }

    @Override
    public boolean delete(K key) {
        Optional<ListNode<K, V>> node = getNode(key);
        if(!node.isPresent()) return false;
        else{
            Optional<ListNode<K, V>> prevNode = node.get().previous;
            Optional<ListNode<K, V>> nextNode = node.get().next;
            if(prevNode.isPresent() && nextNode.isPresent()){
                prevNode.get().next = nextNode;
                nextNode.get().previous = prevNode;
            } else if(!prevNode.isPresent()){
                int index = getIndex(key);
                list.add(index,nextNode);
            } else{
                prevNode.get().next = Optional.empty();
            }
        }
        return true;
    }

    private Optional<ListNode<K, V>> getNode(K key){
        int index = getIndex(key);
        Optional<ListNode<K, V>> node = list.get(index);
        while(node.isPresent() && !node.get().key.equals(key)){
            node = node.get().next;
        }
        return node;
    }


    private int getIndex(K key){
        return Math.abs(key.hashCode() % capacity);
    }
}

class ListNode<K, V>{
    Optional<ListNode<K, V>> next;
    Optional<ListNode<K, V>> previous;
    K key;
    V value;
    ListNode(K key, V value){
        this.key = key;
        this.value = value;
        this.next = Optional.empty();
        this.previous = Optional.empty();
    }
}
