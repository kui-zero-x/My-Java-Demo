package com.example.datastructuresalgorithms.Datastructures.Linked;

import java.util.NoSuchElementException;

public class SingleLinked<E> implements Linked<E>{

    private int size = 0;

    private Node<E> head;

    private Node<E> tail;

    private static class Node<E> {
        E element;
        Node<E> next;

        Node(E e, Node<E> next) {
            this.element = e;
            this.next = next;
        }
    }

    public SingleLinked() {}

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addFirst(E e) {
        Node<E> h = head;
        Node<E> newNode = new Node<>(e, h);
        head = newNode;
        if (h == null) tail = newNode;
        size++;
    }

    @Override
    public void addLast(E e) {
        Node<E> t = tail;
        Node<E> newNode = new Node<>(e, null);
        if (tail != null) {
            tail.next = newNode;
            tail = newNode;
        } else {
            tail = head = newNode;
        }
        size++;
    }

    @Override
    public void add(int index, E e) {
        if (index == 0) {
            addFirst(e);
        } else if (index == (size - 1)) {
            addLast(e);
        } else if (index < 0 || index >= size) {
            throw new NoSuchElementException();
        }
        Node<E> x = head;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                Node<E> temp = x.next;
                Node<E> newNode = new Node<>(e, null);
                x.next = newNode;
                newNode.next = temp;
                break;
            }
            x = x.next;
        }
        size++;
    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public E removeLast() {
        return null;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public E set(int index, E e) {
        return null;
    }

    @Override
    public E getFirst() {
        return null;
    }

    @Override
    public E getLast() {
        return null;
    }

    @Override
    public E get(int index) {
        return null;
    }
}
