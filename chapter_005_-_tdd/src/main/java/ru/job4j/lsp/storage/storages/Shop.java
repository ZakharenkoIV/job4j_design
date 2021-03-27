package ru.job4j.lsp.storage.storages;

import ru.job4j.lsp.storage.products.Food;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Storage {
    private List<Food> storage;

    public Shop() {
        this.storage = new ArrayList<>();
    }

    @Override
    public List<Food> getFoods() {
        return storage;
    }

    @Override
    public boolean addFood(Food food) {
        return storage.add(food);
    }

    @Override
    public boolean accept(Food food) {
        int f = food.freshness();
        boolean result = false;
        if (f > 0 && f < 25) {
            food.setDiscount(5);
            result = true;
        }
        if (f >= 25 && f <= 75) {
            result = true;
        }
        return result;
    }
}
