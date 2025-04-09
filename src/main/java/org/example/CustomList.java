package org.example;

import java.lang.reflect.Array;
import java.util.Comparator;

public class CustomList<E> implements ICustomList<E> {

    private static class Node<E> {
        E item;
        Node<E> prev;
        Node<E> next;

        Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.item = element;
            this.next = next;
        }
    }

    private int size;
    private Node<E> head;
    private Node<E> tail;

    public CustomList() {}

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
        }
        if(a.length > size) {
            for (int i = size; i < a.length; i++) {
                a[i] = null;
            }
        }
        Node<E> currentNode = head;
        int index = 0;
        while (currentNode != null) {
            a[index] = (T)currentNode.item;
            currentNode = currentNode.next;
            index++;
        }
        return a;
    }

    @Override
    public boolean add(E element) {
        Node<E> oldTail = tail;
        Node<E> newNode = new Node<E>(oldTail, element, null);
        if(oldTail == null) {
            head = tail = newNode;
        }else {
            oldTail.next = newNode;
            tail = newNode;
        }
        size++;
        return true;
    }

    @Override
    public void sort(Comparator<? super E> c) {
        //bubbleSort(c, this);
        middleMergeSort(c,this);
    }

    @Override
    public void clear() {
        Node<E> currentNode = head;
        while (currentNode != null) {
            Node<E> nextNode = currentNode.next;
            E removeItem = unlink(currentNode);
            currentNode = nextNode;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CustomList<?> convertObj) {
            if(convertObj.size != this.size) {
                return false;
            }
            Node<E> currentNode = head;
            int compareIndex = 0;
            while (currentNode != null) {
                if(currentNode.item != convertObj.get(compareIndex)){
                    return false;
                }
                currentNode = currentNode.next;
                compareIndex++;
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        Node<E> currentNode = head;
        while (currentNode.next != null) {
            hashCode = 31 * hashCode + (currentNode.item == null ? 0 : currentNode.item.hashCode());
        }
        return hashCode;
    }

    @Override
    public E get(int index){
        if(index < 0 || index >= size) {
            return null;
        }
        Node<E> target = getNodeByIndex(index);
        return target.item;
    }

    @Override
    public E set(int index, E element) {
        if(index < 0 || index >= size) {
            return null;
        }
        Node<E> target = getNodeByIndex(index);
        E oldItem = target.item;
        target.item = element;
        return oldItem;
    }

    @Override
    public void add(int index, E element){
        if(index >= 0 && index <= size) {
            if(index == size) {
                Node<E> newNode = new Node<E>(tail, element, null);
                tail.next = newNode;
                tail = newNode;
            }else {
                Node<E> target = getNodeByIndex(index);
                Node<E> newNode = new Node<E>(target.prev, element, target);
                if(target.prev == null){
                    head = newNode;
                }else {
                    target.prev.next = newNode;
                    target.next.prev = newNode;
                }
            }
            size++;
        }else{
            throw new IndexOutOfBoundsException("out of bounds");
        }
    }

    @Override
    public E remove(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("out of bounds");
        }
        Node<E> target = getNodeByIndex(index);
        if(target.prev == null) {
            head = target.next;
        }else{
            target.prev.next = target.next;
        }
        if(target.next == null) {
            tail = target.prev;
        }else {
            target.next.prev = target.prev;
        }
        unlink(target);
        return target.item;
    }

    @Override
    public int indexOf(Object obj) {
        Node<E> currentNode = head;
        int index = 0;
        while (currentNode != null) {
            if(currentNode.item == convert(obj)) {
                return index;
            }
            currentNode = currentNode.next;
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object obj) {
        Node<E> currentNode = tail;
        int index = size - 1;
        while (currentNode != null) {
            if(currentNode.item == convert(obj)) {
                return index;
            }
            currentNode = currentNode.prev;
            index--;
        }
        return -1;
    }

    @Override
    public void addFirst(E element) {
        Node<E> newNode = new Node<E>(null, element, head);
        if(head == null) {
            head = tail = newNode;
        }else {
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    @Override
    public void addLast(E element) {
        Node<E> newNode = new Node<E>(tail, element, null);
        if(tail == null) {
            head = tail = newNode;
        }else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public E getFirst() {
        return head.item;
    }

    @Override
    public E getLast() {
        return tail.item;
    }

    @Override
    public E removeFirst() {
        E oldItem = head.item;
        unlink(head);
        return oldItem;
    }

    @Override
    public E removeLast() {
        E oldItem = tail.item;
        unlink(tail);
        return oldItem;
    }

    @Override
    public CustomList<E> reversed() {
        Node<E> currentNode = head;
        Node<E> temp = null;
        while (currentNode != null) {
            temp = currentNode.prev;
            currentNode.prev = currentNode.next;
            currentNode.next = temp;
            currentNode = currentNode.prev;
        }
        tail = head;
        if(temp != null) {
            head = temp.prev;
        }
        return this;
    }

    private Node<E> getNodeByIndex(int index) {
        Node<E> target = head;
        int start = 0;
        while (start < index) {
            target = target.next;
            start++;
        }
        return target;
    }

    private E unlink(Node<E> target) {
        Node<E> prev = target.prev;
        Node<E> next = target.next;
        E targetItem = target.item;
        if(prev == null) {
            head = next;
        }else {
            prev.next = next;
        }
        if(next == null) {
            tail = prev;
        }else {
            next.prev = prev;
        }
        target.next = null;
        target.prev = null;
        target.item = null;
        size--;
        return targetItem;
    }

    private E convert(Object obj) {
        return (E) obj;
    }

    private void bubbleSort(Comparator<? super E> c, CustomList<E> list) {
        if(list.size <=1){
            return;
        }
        Node<E> currentNode = list.head;
        while (currentNode != null) {
            Node<E> nextNode = currentNode.next;
            while (nextNode != null) {
                if(c.compare(currentNode.item, nextNode.item) >0) {
                    E tempNodeItem = currentNode.item;
                    currentNode.item = nextNode.item;
                    nextNode.item = tempNodeItem;
                }
                nextNode = nextNode.next;
            }
            currentNode = currentNode.next;
        }
    }

    private void middleMergeSort(Comparator<? super E> c, CustomList<E> list) {
        if(list.size <=1){
            return;
        }
        CustomList<E> left = new CustomList<E>();
        CustomList<E> right = new CustomList<E>();
        int middle = list.size / 2;
        for (int i = 0; i < middle; i++) {
            left.add(list.get(i));
        }
        for (int i = middle; i < list.size; i++) {
            right.add(list.get(i));
        }
        middleMergeSort(c, left);
        middleMergeSort(c, right);
        mergeSort(list, left, right, c);
    }
    private void mergeSort(CustomList<E> list, CustomList<E> left, CustomList<E> right, Comparator<? super E> c) {
        list.clear();
        int leftIndex = 0, rightIndex = 0;
        while (leftIndex <= left.size -1 && rightIndex <= right.size -1) {
            if(c.compare(left.get(leftIndex), right.get(rightIndex)) < 0) {
                list.add(left.get(leftIndex));
                leftIndex++;
            }else{
                list.add(right.get(rightIndex));
                rightIndex++;
            }
        }
        for (int i = leftIndex; i <= left.size -1; i++) {
            list.add(left.get(i));
        }
        for (int i = rightIndex; i <= right.size -1; i++) {
            list.add(right.get(i));
        }
    }

}
