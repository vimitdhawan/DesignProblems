package com.problem.cache;

import java.io.*;
import java.util.*;
import java.lang.*;

/* Sample Input
N = 2
Q = 2
Queries = SET 1 2 GET 1*/

public class LRU{

    public static void main(String[] args) throws IOException {
        BufferedReader read =
                new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(read.readLine());

        while (t-- > 0) {

            int capacity = Integer.parseInt(read.readLine());
            int queries = Integer.parseInt(read.readLine());
            LRUCache cache = new LRUCache(capacity);
            String str[] = read.readLine().trim().split(" ");
            int len = str.length;
            int itr = 0;

            for (int i = 0; (i < queries) && (itr < len); i++) {
                String queryType = str[itr++];
                if (queryType.equals("SET")) {
                    int key = Integer.parseInt(str[itr++]);
                    int value = Integer.parseInt(str[itr++]);
                    cache.set(key, value);
                }
                if (queryType.equals("GET")) {
                    int key = Integer.parseInt(str[itr++]);
                    System.out.print(cache.get(key) + " ");
                }
            }
            System.out.println();
        }
    }
}
// } Driver Code Ends


// design the class in the most optimal way

class LRUCache
{
    private static LinkedList<Integer> list;
    private static Map<Integer, Integer> map;
    private static int size;
    LRUCache(int cap)
    {
        // Intialize the cache capacity with the given
        // cap
        list = new LinkedList<>();
        map = new HashMap<>();
        this.size = cap;

    }

    // This method works in O(1)
    public static int get(int key)
    {
        // your code here
        if(map.containsKey(key)){
            list.remove(new Integer(key));
            list.addFirst(key);
            return map.get(key);
        }

        return -1;


    }

    // This method works in O(1)
    public static void set(int key, int value)
    {
        Integer k = key;
        map.remove(k);
        list.remove(k);
        if(list.size()>=size){
            Integer data = list.removeLast();
            map.remove(data);
        }

        map.put(key, value);
        list.addFirst(k);

    }
}

