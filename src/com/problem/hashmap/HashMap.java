package com.problem.hashmap;

import java.util.ArrayList;
import java.util.List;

public class HashMap<K, V> implements Map<K, V> {
    int capacity;
    private List<ListNode<K, V>> list;

    public HashMap(int capacity){
        this.capacity = capacity;
        list = new ArrayList<>(capacity);
        for(int i =0; i< capacity; i++){
            list.add(null);
        }
    }

    @Override
    public V get(K key) {
        ListNode<K, V> node = getNode(key);
        if(node!=null) {
            return node.value;
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        ListNode<K, V> existingNode = getNode(key);
        if(existingNode!=null){
            existingNode.value = value;
            return;
        }
        int index = getIndex(key);
        ListNode<K, V> node = list.get(index);
        ListNode<K, V> tempNode = new ListNode<>(key, value);
        if(node != null){
            tempNode.next = node;
            node.previous = tempNode;
        }
        list.add(index, tempNode);

    }

    @Override
    public boolean delete(K key) {
        ListNode<K, V> node = getNode(key);
        if(node == null) return false;
        else{
            ListNode<K, V> prevNode = node.previous;
            ListNode<K, V> nextNode = node.next;
            if(prevNode!=null && nextNode!=null){
                prevNode.next = nextNode;
                nextNode.previous = prevNode;
            } else if(prevNode==null){
                int index = getIndex(key);
                list.add(index,nextNode);
            } else{
                prevNode.next = null;
            }
        }
        return true;
    }

    private ListNode<K, V> getNode(K key){
        int index = getIndex(key);
        ListNode<K, V> node = list.get(index);
        while(node!=null && !node.key.equals(key)){
            node = node.next;
        }
        return node;
    }


    private int getIndex(K key){
        return Math.abs(key.hashCode() % capacity);
    }
}

class ListNode<K, V>{
    ListNode<K, V> next;
    ListNode<K, V> previous;
    K key;
    V value;
    ListNode(K key, V value){
        this.key = key;
        this.value = value;
    }
}
