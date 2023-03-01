package com.example.datastructuresalgorithms.Datastructures.Stack;

public interface Stack<E> {

    int size();

    boolean isEmpty();

    void push(E e);

    E pop();
}