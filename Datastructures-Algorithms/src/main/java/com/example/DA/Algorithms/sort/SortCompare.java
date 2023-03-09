package com.example.DA.Algorithms.sort;

import java.util.Random;

public class SortCompare {

    public static <T extends Comparable<T>> long millisecond(String sortAlgorithms, T[] source) {
        long l1 = 0l;
        long l2 = 0l;
        switch (sortAlgorithms) {
            case "Selection" :
                l1 = System.currentTimeMillis();
                new Selection().sort(source);
                l2 = System.currentTimeMillis();
                break;
            case "Insertion" :
                l1 = System.currentTimeMillis();
                new Insertion().sort(source);
                l2 = System.currentTimeMillis();
                break;
        }
        return l2 - l1;
    }

    public static long totalMillisecond(String sortAlgorithms, int N, int T) {
        long total = 0l;
        Random random = new Random();
        Integer[] integers = new Integer[N];
        for (int i = 0; i < T; i++) {
            for (int j = 0; j < N; j++) {
                integers[j] = random.nextInt(101);
            }
            total += millisecond(sortAlgorithms, integers);
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println("Selection / Insertion = " + totalMillisecond("Selection", 20, 20) + "  " + totalMillisecond("Insertion", 20, 20));
    }
}
