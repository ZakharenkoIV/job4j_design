package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private int nodesCounter = 0;

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
        } else {
            foundLastNode().next = node;
        }
        nodesCounter++;
    }

    public T deleteFirst() {
        checkHeadForEmpty();
        T firstElement = head.value;
        if (isOnlyNode()) {
            deleteOnlyElement();
        } else {
            Node<T> firstNode = head;
            head = head.next;
            firstNode.next = null;
        }
        nodesCounter--;
        return firstElement;
    }

    public T deleteLast() {
        checkHeadForEmpty();
        T lastElement;
        if (isOnlyNode()) {
            lastElement = deleteOnlyElement();
        } else {
            lastElement = foundLastNode().value;
            foundPenultimateNode().next = null;
        }
        nodesCounter--;
        return lastElement;
    }

    public void revert() {
        checkHeadForEmpty();
        Node<T> oneNode = head;
        Node<T> twoNode;
            for (int i = 0; i < nodesCounter - 1; i++) {
            twoNode = oneNode.next;
            twoNode.next = oneNode;
            oneNode = twoNode;
        }
        head.next = null;
        head = oneNode;
    }

    private Node<T> foundLastNode() {
        Node<T> lastNode = head;
        while (lastNode.next != null) {
            lastNode = lastNode.next;
        }
        return lastNode;
    }

    private Node<T> foundPenultimateNode() {
        Node<T> lastNode = foundLastNode();
        Node<T> penultimateNode = head;
        while (!penultimateNode.next.equals(lastNode)) {
            penultimateNode = penultimateNode.next;
        }
        return penultimateNode;
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

    private void checkHeadForEmpty() {
        if (head == null) {
            throw new NoSuchElementException();
        }
    }

    private T deleteOnlyElement() {
        Node<T> onlyElement = head;
        head = null;
        return onlyElement.value;
    }

    private boolean isOnlyNode() {
        return head.next == null;
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