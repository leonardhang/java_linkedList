package org.example;

public class Main {
    public static void main(String[] args) {
        CustomList<String> customerList = new CustomList<>() {};
        customerList.add("a");
        customerList.add("b");
        customerList.add("c");
        customerList.add(0,"d");
        customerList.removeLast();
        customerList.sort(String::compareTo);
        int target = 0;
        while (target < customerList.size()) {
            System.out.println(customerList.get(target));
            target++;
        }
    }
}