package org.example;

public class Main {
    public static void main(String[] args) {
//        ["A","A","A","B","B","B", "C","C","C", "D", "D", "E"]
//        char[] input = new char[]{'A', 'A', 'A', 'B','B','B','C','C','C','D', 'D','E'};
        char[] input = new char[]{'A', 'A', 'A', 'B','B','B'};
        int res =Solution.leastInterval(input, 50);
        System.out.println(res);
    }
}