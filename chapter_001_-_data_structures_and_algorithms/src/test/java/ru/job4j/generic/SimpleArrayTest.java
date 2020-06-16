package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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
}
