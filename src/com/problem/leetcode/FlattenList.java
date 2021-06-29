package com.problem.leetcode;
import java.util.*;

public class FlattenList {
    public static void main(String[] args) {
        List<NestedInteger> list = new ArrayList<>();
        list.add(new NestedInteger (Arrays.asList(new NestedInteger(1), new NestedInteger(1))));
        list.add(new NestedInteger(2));
        list.add(new NestedInteger (Arrays.asList(new NestedInteger(1), new NestedInteger(1))));

        NestedIterator iterator = new NestedIterator(list);
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}

class NestedIterator implements Iterator<Integer> {

    private List<Integer> flattenedList;
    private Iterator<Integer> it;

    public NestedIterator(List<NestedInteger> nestedList) {
        flattenedList = new LinkedList<Integer>();
        flatten(nestedList);
        it = flattenedList.iterator();
    }

    private void flatten(List<NestedInteger> nestedList) {
        for (NestedInteger i : nestedList) {
            if (i.isInteger()) {
                flattenedList.add(i.getInteger());
            } else {
                flatten(i.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return it.next();
    }

    @Override
    public boolean hasNext() {
        return it.hasNext();
    }
}

class NestedInteger{
    // @return true if this NestedInteger holds a single integer, rather than a nested list.
      private int singleValue;
      private boolean isSingle;
      private List<NestedInteger> list;
      NestedInteger( int singleValue){
        this.singleValue = singleValue;
        this.isSingle = true;
        this.list = new ArrayList<>();
      }
    NestedInteger( List<NestedInteger> list){
        this.list = list;
    }
      public boolean isInteger(){
        return  isSingle;
      }

              // @return the single integer that this NestedInteger holds, if it holds a single integer
              // Return null if this NestedInteger holds a nested list
       public Integer getInteger(){
          return singleValue;
       }

              // @return the nested list that this NestedInteger holds, if it holds a nested list
              // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList(){
          return list;
        }

}

