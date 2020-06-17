package ru.job4j.generic;

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
        if (position + 1 == objects.length) {
            objects = Arrays.copyOf(objects, objects.length + 10);
        }
        this.objects[position++] = value;
        modCount++;
    }

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

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private final Object[] array = objects;
            private int position = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                boolean result = false;
                if (position < array.length && array[position] != null) {
                    result = true;
                }
                return result;
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) array[position++];
            }
        };
    }
}
