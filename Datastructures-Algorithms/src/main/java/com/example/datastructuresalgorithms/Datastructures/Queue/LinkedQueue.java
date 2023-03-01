package com.example.datastructuresalgorithms.Datastructures.Queue;

import com.example.datastructuresalgorithms.Datastructures.Linked.SingleLinked;
import com.example.datastructuresalgorithms.Datastructures.Stack.ArrayStack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<E> implements Queue<E>, Iterable<E> {

    private int size;

    private transient SingleLinked<E> linked;

    public LinkedQueue() {
        linked = new SingleLinked<>();
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
        linked.addFirst(e);
        size++;
    }

    @Override
    public E poll() {
        if (size == 0) throw new NoSuchElementException("队列为空");
        E e = linked.removeLast();
        size--;
        return e;
    }

    @Override
    public E peek() {
        if (size == 0) throw new NoSuchElementException("队列为空");
        E e = linked.getLast();
        return e;
    }

    @Override
    public Iterator<E> iterator() {
        return linked.iterator();
    }

    @Override
    public String toString() {
        return linked.toString();
    }

    public static void main(String[] args) {
        LinkedQueue<String> queue = new LinkedQueue<>();
        for (int i = 0; i < 9; i++) {
            queue.offer("" + i);
        }
        queue.poll();
        for (String s : queue) {
            System.out.println(s);
        }
        System.out.println(queue);
    }
}
