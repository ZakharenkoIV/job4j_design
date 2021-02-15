package ru.job4j.tdd;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return compare(value, (t) -> comparator.compare(t, value.get(0)) > 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return compare(value, (t) -> comparator.compare(t, value.get(0)) < 0);
    }

    private <T> T compare(List<T> value, Predicate<T> predicate) {
        T rsl = value.get(0);
        for (T t : value) {
            if (predicate.test(t)) {
                rsl = t;
            }
        }
        return rsl;
    }
}