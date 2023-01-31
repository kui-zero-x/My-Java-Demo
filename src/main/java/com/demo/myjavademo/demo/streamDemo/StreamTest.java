package com.demo.myjavademo.demo.streamDemo;

import java.util.Arrays;

public class StreamTest {

    public static void main(String[] args) {
        int[] ints = {1,2,3,4,5,6};
        Arrays.stream(ints).filter(t -> t > 1).forEach(System.out::println);
    }
}
