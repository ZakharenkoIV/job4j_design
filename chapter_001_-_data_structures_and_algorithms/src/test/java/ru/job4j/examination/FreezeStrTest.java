package ru.job4j.examination;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FreezeStrTest {

    @Test
    public void whenEq() {
        assertThat(FreezeStr.eq("Hello", "Hlloe"), is(true));
    }

    @Test
    public void whenNotEq() {
        assertThat(FreezeStr.eq("Hello", "Halle"), is(false));
    }

    @Test
    public void whenNotMultiEq() {
        assertThat(FreezeStr.eq("heloo", "hello"), is(false));
    }
}