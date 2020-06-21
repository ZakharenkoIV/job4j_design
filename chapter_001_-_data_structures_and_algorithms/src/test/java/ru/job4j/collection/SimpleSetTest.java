package ru.job4j.collection;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleSetTest {
    private SimpleSet<String> set = new SimpleSet<>();

    @Before
    public void before() {
        set.add("a");
        set.add("b");
        set.add("c");
    }

    @Test
    public void whenAddUniqueElements() {
        SimpleSet<String> result = new SimpleSet<>();
        result.add("a");
        result.add("b");
        result.add("c");
        assertThat(set, is(result));
    }

    @Test
    public void whenAddRepeatedElement() {
        set.add("b");
        SimpleSet<String> result = new SimpleSet<>();
        result.add("a");
        result.add("b");
        result.add("c");
        assertThat(set, is(result));
    }

    @Test
    public void whenAddNollElement() {
        set.add(null);
        SimpleSet<String> result = new SimpleSet<>();
        result.add("a");
        result.add("b");
        result.add("c");
        result.add(null);
        assertThat(set, is(result));
    }

}
