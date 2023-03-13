package com.example.DA.Algorithms.sort;

import java.util.Random;

public class Quick implements Sort {

    @Override
    public <T extends Comparable<T>> void sort(T[] source) {
        sort(source, 0, source.length - 1);
    }

    private <T extends Comparable<T>> void sort(T[] source, int from, int to) {
        if (to <= from) return;
        int j = partition(source, from, to);// 以 from 为被切分元素
        sort(source, from, j - 1);
        sort(source, j + 1, to);
    }

    private <T extends Comparable<T>> int partition(T[] source, int from, int to) {
        int left = from, right = to + 1; // 左右指针
        flag:
        for (; left <= to; left++) {    // 左指针向右移动
            if (left >= right) break;   // 双指针相遇
            if (compare(source[left], source[from]) == 1) {
                right--;
                for (; right >= from; right--) {          // 右指针向左移动
                    if (right <= left) break flag;        // 双指针相遇
                    if (compare(source[right], source[from]) == -1) {
                        exchange(source, left, right);
                        break;
                    }
                }
            }
        }
        exchange(source, from, left - 1); // 被切分元素放到正确的位置上
        return right - 1;
    }

    public static void main(String[] args) {
        Quick quick = new Quick();
        Random random = new Random();
        Integer[] source = new Integer[20];
        for (int i = 0; i < source.length; i++) {
            source[i] = random.nextInt(21);
        }
        quick.print(source);
        quick.sort(source);
        quick.print(source);
        System.out.println(quick.isSorted(source));
    }
}
