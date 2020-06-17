package ru.job4j.generic;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinked<E> implements Iterable<E> {
    private int size = 0;
    private int modCount = 0;
    private Node<E> first;
    private Node<E> last;

    public void add(E value) {
        last = new Node<>(last, value, null);
        if (size == 0) {
            first = last;
        }
        size++;
        modCount++;
    }

    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> result = first;
            for (int i = 0; i < index; i++) {
                result = result.next;
            }
        return result.item;
    }

    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int expectedModCount = modCount;
            private Node<E> nextNode = first;

            @Override
            public boolean hasNext() {
                return nextNode != null;
            }

            @Override
            public E next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> result = nextNode;
                nextNode = result.next;
                return result.item;
            }
        };
    }

    public static class Node<E> {
        private E item;
        private Node<E> next;
        private Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
