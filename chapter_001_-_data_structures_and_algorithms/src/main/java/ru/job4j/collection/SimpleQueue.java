package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int inCounter = 0;
    private int outCounter = 0;

    public T poll() {
        assert outCounter >= 0 : "outCounter < 0";
        if (outCounter == 0) {
            for (int i = 0; i < inCounter; i++) {
                out.push(in.pop());
            }
            outCounter = inCounter;
            inCounter = 0;
        }
        outCounter--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        inCounter++;
    }
}