package com.demo.myjavademo.demo.optional;

import java.util.Optional;

public class OptionalTest {

    public static void main(String[] args) {
        String s = null;
        System.out.println(Optional.ofNullable(s));
    }
}
