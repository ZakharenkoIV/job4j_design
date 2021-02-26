package ru.job4j.lsp.product_storage.storages;

import ru.job4j.lsp.product_storage.products.Food;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Storage {
    private List<Food> storage;

    public Shop() {
        this.storage = new ArrayList<>();
    }

    @Override
    public boolean addFood(Food food) {
        return storage.add(food);
    }
}
