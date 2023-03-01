package com.example.datastructuresalgorithms.Datastructures.Queue;

public interface Queue<E> {

    int size();

    boolean isEmpty();

    // 插入一个元素到队尾
    void offer(E e);

    // 移除并返回队首元素
    E poll();

    // 返回单不移除对首元素
    E peek();
}
