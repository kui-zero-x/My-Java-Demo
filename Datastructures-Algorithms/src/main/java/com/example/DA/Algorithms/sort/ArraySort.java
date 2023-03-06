package com.example.DA.Algorithms.sort;

import java.util.Arrays;

public interface ArraySort<T> {

    <T extends Comparable<T>> void sort(T[] source);

    default  <T extends Comparable<T>> int compare(T a, T b) {
        return a.compareTo(b);
    }

    default  <T extends Comparable<T>> void exchange(T[] source, int i, int j) {
        T temp = source[i];
        source[i] = source[j];
        source[j] = temp;
    }

    default  <T extends Comparable<T>> boolean isSorted(T[] target) {
        boolean flag = true;
        for (int i = 0; i < target.length - 1; i++) {
            if (target[i].compareTo(target[i + i]) == 1) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    default  <T extends Comparable<T>> String print(T[] target) {
        return Arrays.toString(target);
    }
}
