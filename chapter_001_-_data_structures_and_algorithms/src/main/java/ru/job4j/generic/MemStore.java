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
            for (int i = 0; i < mem.size(); i++) {
                if (t != null && mem.get(i).equals(t)) {
                    mem.set(i, model);
                    result = true;
                    break;
                }
            }
        return result;
    }

    @Override
    public boolean delete(String id) {
        return mem.remove(findById(id));
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