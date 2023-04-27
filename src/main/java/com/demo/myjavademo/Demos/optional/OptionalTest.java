package com.demo.myjavademo.Demos.optional;

import java.util.Optional;

/**
 * Optional 专注于解决NPE问题
 */
public class OptionalTest {

    public static void main(String[] args) {
        String s = "hello";
        System.out.println(Optional.ofNullable(s).map(t -> t = null).filter(t -> t == null));
    }
}
