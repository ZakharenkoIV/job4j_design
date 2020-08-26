package ru.job4j.examination;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AnalyzeTest {

    @Test
    public void whenPreviousEqualsCurrent() {
        List<Analyze.User> previous = new ArrayList<>();
        previous.add(new Analyze.User(1, "№1"));
        List<Analyze.User> current = new ArrayList<>();
        current.add(new Analyze.User(1, "№1"));
        Analyze.Info info = new Analyze.Info();
        info.added = 0;
        info.changed = 0;
        info.deleted = 0;
        assertThat(new Analyze().diff(previous, current), is(info));
    }

    @Test
    public void whenPreviousAdded() {
        List<Analyze.User> previous = new ArrayList<>();
        List<Analyze.User> current = new ArrayList<>();
        current.add(new Analyze.User(1, "№1"));
        Analyze.Info info = new Analyze.Info();
        info.added = 1;
        info.changed = 0;
        info.deleted = 0;
        assertThat(new Analyze().diff(previous, current), is(info));
    }

    @Test
    public void whenPreviousChanged() {
        List<Analyze.User> previous = new ArrayList<>();
        previous.add(new Analyze.User(1, "№1"));
        List<Analyze.User> current = new ArrayList<>();
        current.add(new Analyze.User(1, "№2"));
        Analyze.Info info = new Analyze.Info();
        info.added = 0;
        info.changed = 1;
        info.deleted = 0;
        assertThat(new Analyze().diff(previous, current), is(info));
    }

    @Test
    public void whenPreviousDeleted() {
        List<Analyze.User> previous = new ArrayList<>();
        previous.add(new Analyze.User(1, "№1"));
        List<Analyze.User> current = new ArrayList<>();
        Analyze.Info info = new Analyze.Info();
        info.added = 0;
        info.changed = 0;
        info.deleted = 1;
        assertThat(new Analyze().diff(previous, current), is(info));
    }
}
