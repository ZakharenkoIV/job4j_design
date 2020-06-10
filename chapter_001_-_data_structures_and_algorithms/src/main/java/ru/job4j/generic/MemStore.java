package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        T t = findById(id);
        if (t != null) {
            int index = 0;
            for (T tt : mem) {
                if (tt.equals(t)) {
                    mem.set(index, model);
                    result = true;
                }
                index++;
            }
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result;
        T t = findById(id);
        if (t != null) {
            result = mem.remove(t);
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public T findById(String id) {
        T result = null;
        for (T t : mem) {
            if (t.getId().equals(id)) {
                result = t;
                break;
            }
        }
        return result;
    }
}