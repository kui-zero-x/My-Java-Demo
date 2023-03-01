package com.example.datastructuresalgorithms.Datastructures.Linked;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SingleLinked<E> implements Linked<E>, Iterable<E> {

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

    public SingleLinked() {
    }

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
            return;
        } else if (index == (size - 1)) {
            addLast(e);
            return;
        } else if (index < 0 || index >= size) {
            throw new NoSuchElementException();
        }
        Node<E> x = head;
        for (int i = 1; i < size - 1; i++) {
            x = x.next;
            if (i == index) {
                Node<E> temp = x.next;
                Node<E> newNode = new Node<>(e, null);
                x.next = newNode;
                newNode.next = temp;
                break;
            }
        }
        size++;
    }

    @Override
    public E removeFirst() {
        if (head == null) throw new NoSuchElementException();
        E element = head.element;
        Node<E> next = head.next;
        head.next = null;
        head.element = null; // help gc
        head = next;
        size--;
        return element;
    }

    @Override
    public E removeLast() {
        if (tail == null) throw new NoSuchElementException();
        E element = tail.element;
        Node<E> prev = head;
        for (int i = 1; i < size - 1; i++) {
            prev = prev.next;
        }
        prev.next = null;
        tail = prev;
        size--;
        return element;
    }

    @Override
    public E remove(int index) {
        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else if (index < 0 || index >= size) {
            throw new NoSuchElementException();
        }
        Node<E> x = head;
        Node<E> y = head;
        E element = null;
        flag:
        for (int i = 1; i < size - 1; i++) {
            x = x.next;
            if (index == i) {
                for (int j = 0; j < size; j++) {
                    if (j == i - 1) {
                        y.next = x.next;
                        element = x.element;
                        x.next = null;
                        x.element = null; // help gc
                        break flag;
                    }
                    y = y.next;
                }
            }
        }
        size--;
        return element;
    }

    @Override
    public E set(int index, E e) {
        E oldValue = null;
        if (index < 0 || index >= size) throw new NoSuchElementException();
        Node<E> x = head;
        for (int i = 0; i < size; i++) {
            if (index == i) {
                x.element = e;
                break;
            }
            x = x.next;
        }
        return oldValue;
    }

    @Override
    public E getFirst() {
        if (head == null) throw new NoSuchElementException();
        return head.element;
    }

    @Override
    public E getLast() {
        if (tail == null) throw new NoSuchElementException();
        return tail.element;
    }

    @Override
    public E get(int index) {
        if (index == 0) {
            return getFirst();
        } else if (index == size - 1) {
            return getLast();
        } else if (index < 0 || index >= size) {
            throw new NoSuchElementException();
        }
        Node<E> x = head;
        E element = null;
        for (int i = 1; i < size - 1; i++) {
            x = x.next;
            if (index == i) {
                element = x.element;
            }
        }
        return element;
    }

    @Override
    public String toString() {
        Node<E> x = head;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append("[" + x.element + ",pointer] --> ");
            x = x.next;
        }
        sb.delete(sb.length() - 5, sb.length() - 1);
        return sb.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedIterator<>();
    }

    private class LinkedIterator<E> implements Iterator<E> {

        // 迭代器初始位置在链表头部前面
        private Node<E> nextNode = (Node<E>) head;

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public E next() {
            E element = nextNode.element;
            nextNode = nextNode.next;
            return element;
        }
    }

    public static void main(String[] args) {
        SingleLinked<String> singleLinked = new SingleLinked<>();
        for (int i = 0; i < 9; i++) {
            singleLinked.addLast(i + "");
        }
        for (String s : singleLinked) {
            System.out.println(s);
        }
    }
}
