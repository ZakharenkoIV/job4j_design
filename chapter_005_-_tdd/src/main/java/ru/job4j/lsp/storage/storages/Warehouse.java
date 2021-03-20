package ru.job4j.lsp.storage.storages;

import ru.job4j.lsp.storage.products.Food;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Storage {
    private List<Food> storage;

    public Warehouse() {
        this.storage = new ArrayList<>();
    }

    @Override
    public boolean addFood(Food food) {
        return storage.add(food);
    }

    @Override
    public boolean accept(Food food) {
        return food.freshness() > 75;
    }
}
