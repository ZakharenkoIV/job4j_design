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
    public boolean delete(String id) {
        return mem.remove(findById(id));
    }

    @Override
    public T findById(String id) {
        return mem.get(indexOf(id));
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        int index = indexOf(id);
        if (index != -1) {
            mem.set(indexOf(id), model);
            result = true;
        }
        return result;
    }

    private int indexOf(String id) {
        int index = -1;
        for (int i = 0; i < mem.size(); i++) {
            if (mem.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }
        return index;
    }
}