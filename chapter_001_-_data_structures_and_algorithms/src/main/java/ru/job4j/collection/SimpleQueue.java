package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int counter = 0;

    public T poll() {
        stackShift(out, in);
        T result = out.pop();
        counter--;
        stackShift(in, out);
        return result;
    }

    public void push(T value) {
        in.push(value);
        counter++;
    }

    private void stackShift(SimpleStack<T> in, SimpleStack<T> out) {
        for (int i = 0; i < counter; i++) {
            in.push(out.pop());
        }
    }
}