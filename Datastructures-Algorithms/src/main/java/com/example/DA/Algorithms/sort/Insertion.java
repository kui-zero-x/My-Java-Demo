package com.example.DA.Algorithms.sort;

import java.util.Random;

public class Insertion implements Sort {


    @Override
    public <T extends Comparable<T>> void sort(T[] source) {
        int length = source.length;
        for (int i = 1; i < length; i++) {
            T temp = source[i];
            for (int j = 0; j < i; j++) {
                if (compare(temp, source[j]) == -1) {
                    System.arraycopy(source, j, source, j + 1, i - j);
                    source[j] = temp;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Insertion insertion = new Insertion();
        Random random = new Random();
        Integer[] source = new Integer[20];
        for (int i = 0; i < source.length; i++) {
            source[i] = random.nextInt(21);
        }
        insertion.print(source);
        insertion.sort(source);
        insertion.print(source);
        System.out.println(insertion.isSorted(source));
    }
}
