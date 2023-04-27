package com.demo.myjavademo.Utils.bean;


import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * bean拷贝工具类
 */
public class BeanCopyUtil extends BeanUtils {

    /**
     * 集合数据的拷贝
     *
     * @param sources: 要拷贝的List
     * @param target:  Class::new(eg: User::new)
     * @return
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
        List<T> list = new ArrayList<>(sources.size());
        for (S source : sources) {
            T t = target.get();
            copyProperties(source, t);
            list.add(t);
        }
        return list;
    }
}
