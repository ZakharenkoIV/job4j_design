package ru.job4j.generic;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] objects;
    private int index = 0;

    public SimpleArray(int arraySize) {
        this.objects = new Object[arraySize];
    }

    public void add(T value) {
        this.objects[index++] = value;
    }

    public T get(int position) {
        return (T) this.objects[position];
    }

    public void set(int index, T model) {
        objects[index] = model;
    }


    public void remove(int index) {
        if (objects.length - 1 - index >= 0) {
            System.arraycopy(objects, index + 1, objects, index, objects.length - 1 - index);
            objects[objects.length - 1] = null;
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
        return index == that.index && Arrays.equals(objects, that.objects);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(index);
        result = 31 * result + Arrays.hashCode(objects);
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        Object[] array = objects;
        return new Iterator<>() {

            private final Object[] objects = array;
            private int position = 0;

            @Override
            public boolean hasNext() {
                boolean result = false;
                if (position < objects.length && objects[position] != null) {
                    result = true;
                }
                return result;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) objects[position++];
            }
        };
    }
}
