package ru.job4j.collection;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleLinkedTest {
    private SimpleLinked<Integer> simpleLinked = new SimpleLinked<>();

    @Test
    public void whenAddThenGet() {
        simpleLinked.add(1);
        assertThat(simpleLinked.get(0), is(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        simpleLinked.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        simpleLinked.add(1);
        simpleLinked.add(1);
        simpleLinked.get(2);
    }

    @Test
    public void whenAddThenIt() {
        simpleLinked.add(1);
        int rsl = simpleLinked.iterator().next();
        assertThat(rsl, is(1));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        simpleLinked.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        Iterator<Integer> it = simpleLinked.iterator();
        simpleLinked.add(1);
        it.next();
    }
}
