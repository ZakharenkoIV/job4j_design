package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayTest {
    private SimpleArray<String> simpleArray = new SimpleArray<>(3);

    @Before
    public void before() {
        simpleArray.add("test1");
        simpleArray.add("test2");
        simpleArray.add("test3");
    }

    @Test
    public void add() {

        assertThat(simpleArray.toString(), is("SimpleArray [test1, test2, test3]"));
    }

    @Test
    public void get() {
        assertThat(simpleArray.get(1), is("test2"));
    }

    @Test
    public void set() {
        simpleArray.set(1, "test7");
        assertThat(simpleArray.get(1), is("test7"));
    }

    @Test
    public void remove() {
        simpleArray.remove(1);
        assertThat(simpleArray.toString(), is("SimpleArray [test1, test3, null]"));
    }

    @Test
    public void iterator() {
        SimpleArray<String> result = new SimpleArray<>(3);
        for (String s : simpleArray) {
            result.add(s);
        }
        simpleArray.iterator();
        assertThat(result, is(simpleArray));
    }

    @Test
    public void whenAddThenGet() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("first");
        String rsl = array.get(0);
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddThenIt() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("first");
        String rsl = array.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        SimpleArray<String> array = new SimpleArray<>();
        array.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        simpleArray.get(3);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleArray<String> array = new SimpleArray<>();
        array.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        Iterator<String> it = simpleArray.iterator();
        simpleArray.add("test4");
        it.next();
    }
}
