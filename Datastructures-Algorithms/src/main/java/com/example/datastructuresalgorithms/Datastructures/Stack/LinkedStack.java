package com.example.datastructuresalgorithms.Datastructures.Stack;

import com.example.datastructuresalgorithms.Datastructures.Linked.SingleLinked;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<E> implements Stack<E>, Iterable<E> {

    private int size;

    private transient SingleLinked<E> linked;

    public LinkedStack() {
        linked = new SingleLinked<>();
    };

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void push(E e) {
        linked.addLast(e);
        size++;
    }

    @Override
    public E pop() {
        if (size == 0) throw new NoSuchElementException("栈为空");
        E element = linked.removeLast();
        size--;
        return element;
    }

    @Override
    public String toString() {
        return linked.toString();
    }


    @Override
    public Iterator<E> iterator() {
        return linked.iterator();
    }

    public static void main(String[] args) {
        LinkedStack<String> stack = new LinkedStack<>();
        for (int i = 0; i < 9; i++) {
            stack.push("" + i);
        }
        stack.push("9");
        for (String s : stack) {
            System.out.println(s);
        }
        System.out.println(stack);
    }

}
