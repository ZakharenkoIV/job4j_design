package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int counter = 0;

    public T poll() {
        for (int i = 0; i < counter; i++) {
            out.push(in.pop());
        }
        counter--;
        T result = out.pop();
        for (int i = 0; i < counter; i++) {
            in.push(out.pop());
        }
        return result;
    }

    public void push(T value) {
        in.push(value);
        counter++;
    }
}