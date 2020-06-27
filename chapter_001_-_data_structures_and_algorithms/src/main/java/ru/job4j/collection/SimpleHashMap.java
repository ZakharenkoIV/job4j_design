package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMap<K, V> implements Iterable<SimpleHashMap.Node<K, V>> {
    private Node<K, V>[] table;
    private int size = 0;
    private int capacity = 1 << 4;
    private int modCount = 0;

    public SimpleHashMap() {
        this.table = (Node<K, V>[]) new Node[capacity];
    }

    boolean insert(K key, V value) {
        checkLoadFactor();
        boolean result = false;
        Node<K, V> newNode = new Node<>(key, value);
        int index = toCountIndexForNode(key);
        if (table[index] == null) {
            table[index] = newNode;
            size++;
            modCount++;
            result = true;
        }
        return result;
    }

    V get(K key) {
        return table[toCountIndexForNode(key)].value;
    }

    boolean delete(K key) {
        boolean result = false;
        int index = toCountIndexForNode(key);
        if (table[index] != null) {
            table[index] = null;
            size--;
            modCount++;
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<Node<K, V>> iterator() {
        return new Iterator<>() {
            private int checkSize = 0;
            private int expectedModCount = modCount;
            private int index = 0;

            @Override
            public boolean hasNext() {
                return checkSize != size;
            }

            @Override
            public Node<K, V> next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<K, V> result = null;
                while (index < table.length) {
                    if (table[index] != null) {
                        result = table[index];
                        checkSize++;
                        index++;
                        break;
                    }
                    index++;
                }
                return result;
            }
        };
    }

    static class Node<K, V> {
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private void checkLoadFactor() {
        if ((double) size / (double) capacity > 0.75) {
            capacity = capacity << 1;
            Node<K, V>[] newTable = (Node<K, V>[]) new Node[capacity];
            for (Node<K, V> node : table) {
                if (node != null) {
                    newTable[toCountIndexForNode(node.key)] = node;
                    table = newTable;
                }
            }
        }
    }

    private int toCountIndexForNode(K key) {
        int hash = key.hashCode() ^ (key.hashCode() >>> 16);
        return hash % table.length;
    }
}
