package com.problem.hashmap;

public class App {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>(20);
        map.put("vimit", 0);
        System.out.println(map.get("vimit"));
        map.put("ram",1);
        map.put("vimit",2);
        System.out.println(map.get("vimit"));
        System.out.println(map.delete("vimit"));
        System.out.println(map.delete("vimit"));
    }
}
