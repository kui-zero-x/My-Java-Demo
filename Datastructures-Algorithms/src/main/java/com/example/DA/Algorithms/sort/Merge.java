package com.example.DA.Algorithms.sort;

import java.util.Random;

public class Merge implements Sort {

    private String type;
    private static Comparable[] t;

    public Merge(String type) {
        this.type = type;
    }

    @Override
    public <T extends Comparable<T>> void sort(T[] source) {
        int length = source.length;
        t = new Comparable[length];
        if (this.type.equals("top-to-down")) {
            sortTopToDown(source, 0, length - 1);
        } else if (this.type.equals("down-to-top")) {
            sortDownToTop(source, 0, length - 1);
        }
    }

    /**
     * 自顶向下的归并（递归的归并两边的有序数组）
     *
     * @param source
     * @param <T>
     */
    private <T extends Comparable<T>> void sortTopToDown(T[] source, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        sortTopToDown(source, left, mid);
        sortTopToDown(source, mid + 1, right);
        merge(source, left, mid, right);
    }

    private <T extends Comparable<T>> void sortDownToTop(T[] source, int left, int right) {
        int length = source.length;
        for (int i = 1; i < length; i *= 2) { // 每轮每个子数组有 1 2 4 8 16 ... 个元素
            for (int j = 0; j < length; j += 2 * i) {
                merge(source, j, j + i - 1, Math.min(j + 2 * i - 1, length - 1));
            }
        }
    }

    public <T extends Comparable<T>> void merge(T[] source, int from, int mid, int to) {
        int i = from;
        int j = mid + 1;
        // 拷贝要归并的数组
        for (int k = from; k <= to; k++) {
            t[k] = source[k];
        }
        // 归并
        for (int k = from; k <= to; k++) {
            if (i > mid) {
                source[k] = (T) t[j++];
            } else if (j > to) {
                source[k] = (T) t[i++];
            } else if (compare(t[i], t[j]) == 1) {
                source[k] = (T) t[j++];
            } else {
                source[k] = (T) t[i++];
            }
        }
    }

    public static void main(String[] args) {
        Merge merge = new Merge("down-to-top");
        Random random = new Random();
        Integer[] source = new Integer[20];
        for (int i = 0; i < source.length; i++) {
            source[i] = random.nextInt(21);
        }
        merge.print(source);
        merge.sort(source);
        merge.print(source);
        System.out.println(merge.isSorted(source));
    }
}
