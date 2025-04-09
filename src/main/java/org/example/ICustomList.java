package org.example;

import java.util.Comparator;

public interface ICustomList<E> {

    int size();

    boolean isEmpty();

    <T> T[] toArray(T[] a);

    boolean add(E e);

    void sort(Comparator<? super E> c);

    void clear();

    boolean equals(Object o);

    int hashCode();

    E get(int index);

    E set(int index, E element);

    void add(int index, E element);

    E remove(int index);

    int indexOf(Object o);

    int lastIndexOf(Object o);

    void addFirst(E e);

    void addLast(E e);

    E getFirst();

    E getLast();

    E removeFirst();

    E removeLast();

    ICustomList<E> reversed();

}
