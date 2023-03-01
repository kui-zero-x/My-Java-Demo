package com.example.datastructuresalgorithms.Datastructures.Stack;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack<E> implements Stack<E>, Iterable<E> {

    private int size;

    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10;

    public ArrayStack() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayStack(int capacity) {
        if (capacity < 0) {
            throw new IndexOutOfBoundsException("索引不能为负");
        } else if (capacity <= DEFAULT_CAPACITY) {
            elements = (E[]) new Object[DEFAULT_CAPACITY];
        } else {
            elements = (E[]) new Object[capacity];
        }
    }

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
        if (size == elements.length) {
            incre();
        }
        elements[size++] = e;
    }

    @Override
    public E pop() {
        if (size == 0) throw new NoSuchElementException();
        int x = --size;
        E element = elements[x];
        elements[x] = null; // help gc
        return element;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayStackIterator<E>();
    }

    private class ArrayStackIterator<E> implements Iterator<E> {

        private int index = -1;

        @Override
        public boolean hasNext() {
            return !(size == 0 || index == size - 1);
        }

        @Override
        public E next() {
            return (E) elements[++index];
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }

    private void incre() {
        int newCapacity = elements.length * 2;
        E[] temp = (E[]) new Object[newCapacity];
        System.arraycopy(elements, 0, temp, 0, elements.length);
        elements = temp;
    }

    private void decre() {
        int newCapacity = elements.length / 2;
        E[] temp = (E[]) new Object[newCapacity];
        System.arraycopy(elements, 0, temp, 0, elements.length);
        elements = temp;
    }

    public static void main(String[] args) {
        ArrayStack<String> stack = new ArrayStack<>();
        for (int i = 0; i < 9; i++) {
            stack.push("" + i);
        }
        for (String s : stack) {
            System.out.println(s);
        }
        System.out.println(stack);
    }
}
