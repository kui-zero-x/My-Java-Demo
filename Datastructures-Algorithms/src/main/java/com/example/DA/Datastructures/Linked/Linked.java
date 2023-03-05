package com.example.DA.Datastructures.Linked;

public interface Linked<E> {

    int size();

    void addFirst(E e);
    void addLast(E e);
    void add(int index, E e);

    E removeFirst();
    E removeLast();
    E remove(int index);

    E set(int index, E e);

    E getFirst();
    E getLast();
    E get(int index);
}
