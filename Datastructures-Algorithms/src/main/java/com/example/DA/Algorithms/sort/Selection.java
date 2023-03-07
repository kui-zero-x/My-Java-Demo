package com.example.DA.Algorithms.sort;

import java.util.Random;

public class Selection implements Sort {

    @Override
    public <T extends Comparable<T>> void sort(T[] source) {

        int length = source.length;
        for (int i = 0; i < length - 1; i++) {
            int minIndex = i;
            for (int j = i; j < length; j++) {
                if (compare(source[j], source[minIndex]) == -1) minIndex = j;
            }
            exchange(source, i, minIndex);
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        Selection selection = new Selection();
        Integer[] source = new Integer[20];
        for (int i = 0; i < source.length; i++) {
            source[i] = random.nextInt(21);
        }
        selection.print(source);
        selection.sort(source);
        selection.print(source);
    }
}
