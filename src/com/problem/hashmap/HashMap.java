package com.problem.hashmap;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HashMap<K, V> implements Map<K, V> {

    private class ListNode{
        Optional<ListNode> next;
        Optional<ListNode> previous;
        K key;
        V value;
        ListNode(K key, V value){
            this.key = key;
            this.value = value;
            this.next = Optional.empty();
            this.previous = Optional.empty();
        }

        private boolean notEqual(K key){
            return !this.key.equals(key);
        }

        private void setValue(V value){
            this.value = value;
        }

        private void addNodeAtHead(ListNode node){
            node.next = Optional.of(this);
            this.previous = Optional.of(node);
        }

    }

    private int capacity;
    private List<Optional<ListNode>> list ;

    public HashMap(int capacity){
        this.capacity = capacity;
        list = initializeList(capacity);
    }

    private List<Optional<ListNode>> initializeList(int capacity){
        list = new ArrayList<>(capacity);
        for(int i =0; i< capacity; i++){
            list.add(Optional.empty());
        }
        return list;
    }

    @Override
    public Optional<V> get(K key) {
        Optional<ListNode> node = getNodeWithKey(key);
        if(node.isPresent()) {
            return Optional.of(node.get().value);
        }
        return Optional.empty();
    }

    @Override
    public Optional<V> put(K key, V value) {
       Optional<V> maybeExistingValue =  updateValueIfExist(key, value);
        if(!maybeExistingValue.isPresent()){
            addNewValue(key, value);
        }
        return maybeExistingValue;
    }

    private void addNewValue(K key, V value){
        int bucketIndex = getBucket(key);
        Optional<ListNode> maybeHead = list.get(bucketIndex);
        Optional<ListNode> newNode = Optional.of(new ListNode(key, value));
        if(maybeHead.isPresent()){
            maybeHead.get().addNodeAtHead(newNode.get());
        }
        list.add(bucketIndex, newNode);
    }

    private Optional<V> updateValueIfExist(K key, V value){
        Optional<ListNode> maybeExistingNode = getNodeWithKey(key);
        if(maybeExistingNode.isPresent()){
            V existingValue = maybeExistingNode.get().value;
            maybeExistingNode.get().setValue(value);
            return Optional.of(existingValue);
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(K key) {
        Optional<ListNode> node = getNodeWithKey(key);
        if(!node.isPresent()) return false;
        else{
            Optional<ListNode> prevNode = node.get().previous;
            Optional<ListNode> nextNode = node.get().next;
            if(prevNode.isPresent() && nextNode.isPresent()){
                prevNode.get().next = nextNode;
                nextNode.get().previous = prevNode;
            } else if(!prevNode.isPresent()){
                int index = getBucket(key);
                list.add(index,nextNode);
            } else{
                prevNode.get().next = Optional.empty();
            }
        }
        return true;
    }

    private Optional<ListNode> getNodeWithKey(K key){
        int bucket = getBucket(key);
        Optional<ListNode> maybeNode = list.get(bucket); // its better to use good naming convention
        while(maybeNode.isPresent() && maybeNode.get().notEqual(key)){  // here we iterating list if we all the collision on same bucket we have to use
            maybeNode = maybeNode.get().next;
        }
        return maybeNode;
    }


    private int getBucket(K key){
        return Math.abs(key.hashCode() % capacity);
    }
}

