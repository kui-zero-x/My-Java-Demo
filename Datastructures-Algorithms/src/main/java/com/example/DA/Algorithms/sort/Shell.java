package com.example.DA.Algorithms.sort;

import java.util.Random;

public class Shell implements Sort {


    @Override
    public <T extends Comparable<T>> void sort(T[] source) {

        int length = source.length;
        int h = 1;
        while (h < length / 3) h = h * 3 + 1; // 1 4 13 40 ...
        while (h >= 1) {
            // h-sort
            for (int i = h; i < length; i++) {
                for (int j = i; j >= h && compare(source[j], source[j - h]) == -1; j -= h) {
                    exchange(source, j, j - h);
                }
            }
            h /= 3;
        }
    }

    public static void main(String[] args) {
        Shell shell = new Shell();
        Random random = new Random();
        Integer[] source = new Integer[20];
        for (int i = 0; i < source.length; i++) {
            source[i] = random.nextInt(21);
        }
        shell.print(source);
        shell.sort(source);
        shell.print(source);
        System.out.println(shell.isSorted(source));
    }
}
