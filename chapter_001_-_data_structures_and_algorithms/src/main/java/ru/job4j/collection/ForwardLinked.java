package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public void deleteFirst() {
        if (!isEmptyOrOneNode()) {
            Node<T> headNode = head;
            head = headNode.next;
            headNode.next = null;
        }
    }

    public void deleteLast() {
        if (!isEmptyOrOneNode()) {
            Node<T> lastNode = head;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }
            Node<T> penultimateNode = head;
            if (penultimateNode.next != null) {
                while (!penultimateNode.next.equals(lastNode)) {
                    penultimateNode = penultimateNode.next;
                }
            }
            penultimateNode.next = null;
        }
    }

    private boolean isEmptyOrOneNode() {
        boolean result = false;
        if (head == null) {
            throw new NoSuchElementException();
        } else if (head.next == null) {
            head = null;
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}