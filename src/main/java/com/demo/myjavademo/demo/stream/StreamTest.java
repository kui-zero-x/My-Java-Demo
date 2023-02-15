package com.demo.myjavademo.demo.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(new Integer[]{1, 1, 1, 4, 5, 6}));
        System.out.println(Stream.concat(list.stream().filter(t -> t > 2).distinct().map(t -> ++t), list.stream().filter(t -> t == 1)).collect(Collectors.toList()));
    }

    /**
     * 在执行返回 Stream 的方法时，并不立刻执行，而是等返回一个非 Stream 的方法后才执行
     */
    @Test
    public void laziness() {
        List<String> strings = Arrays.asList("abc", "def", "gkh", "abc");
        Stream<Integer> stream = strings.stream().filter(new Predicate() {
            @Override
            public boolean test(Object o) {
                System.out.println("Predicate.test 执行------" + o);
                return true;
            }
        });

        System.out.println("count 执行");
        stream.count();
    }

    /**
     * Collectors.join 方法测试
     */
    @Test
    public void join_test() {
        List<Integer> list = new ArrayList<>(Arrays.asList(new Integer[]{1, 1, 1, 4, 5, 6}));
        System.out.println(list.stream().map(String::valueOf).collect(Collectors.joining("|", "[", "]")));
    }
}
