package com.example.DA.Datastructures.Queue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayQueue<E> implements Queue<E>, Iterable<E> {

    private int size;

    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10;

    public ArrayQueue() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayQueue(int capacity) {
        if (capacity <= DEFAULT_CAPACITY) {
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
    public void offer(E e) {
        if (size == elements.length) increase();
        elements[elements.length - size - 1] = e;
        size++;
    }

    @Override
    public E poll() {
        if (size == 0) throw new NoSuchElementException("列表为空");
        E e = elements[elements.length - 1];
        size--;
        System.arraycopy(elements, elements.length - 1 - size, elements, elements.length - size, size);
        elements[elements.length - size - 2] = null;
        return e;
    }

    @Override
    public E peek() {
        if (size == 0) throw new NoSuchElementException("列表为空");
        E e = elements[elements.length - 1];
        return e;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayQueueIterator<>();
    }

    private class ArrayQueueIterator<E> implements Iterator<E> {

        private int now = elements.length;

        @Override
        public boolean hasNext() {
            return !(size == 0 || now == elements.length - size);
        }

        @Override
        public E next() {
            return (E) elements[--now];
        }
    }

    @Override
    public String toString() {
        return "ArrayQueue" + Arrays.toString(elements);
    }

    private void increase() {
        int newCapacity = size * 2;
        E[] temp = (E[]) new Object[newCapacity];
        System.arraycopy(elements, 0, temp, size, size);
        elements = temp;
    }

    public static void main(String[] args) {
        ArrayQueue<String> queue = new ArrayQueue<>();
        for (int i = 0; i < 11; i++) {
            queue.offer("" + i);
        }
        queue.poll();
        for (String s : queue) {
            System.out.println(s);
        }
        System.out.println(queue);
    }
}
