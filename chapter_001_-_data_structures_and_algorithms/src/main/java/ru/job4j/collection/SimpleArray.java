package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] objects;
    private int position = 0;
    private int modCount = 0;

    public SimpleArray() {
        this.objects = new Object[10];
    }

    public SimpleArray(int arraySize) {
        this.objects = new Object[arraySize];
    }

    public void add(T value) {
        if (position == objects.length) {
            objects = Arrays.copyOf(objects, objects.length + 10);
        }
        this.objects[position++] = value;
        modCount++;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        Objects.checkIndex(index, position);
        return (T) this.objects[index];
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, position);
        objects[index] = model;
        modCount++;
    }

    public void remove(int index) {
        Objects.checkIndex(index, position);
        if (objects.length - 1 - index >= 0) {
            System.arraycopy(objects, index + 1, objects, index, objects.length - 1 - index);
            objects[objects.length - 1] = null;
            modCount++;
        }
    }

    @SuppressWarnings("unchecked")
    boolean contains(T model) {
        boolean result = false;
        if (position != 0) {
            for (Object o : objects) {
                T m = (T) o;
                if (Objects.equals(m, model)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "SimpleArray " + Arrays.toString(objects);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleArray<?> that = (SimpleArray<?>) o;
        return position == that.position && Arrays.equals(objects, that.objects);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(position);
        result = 31 * result + Arrays.hashCode(objects);
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private final Object[] array = objects;
            private int index = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return index < position;
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) array[index++];
            }
        };
    }
}
