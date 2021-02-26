package ru.job4j.lsp.product_storage.storages;

import ru.job4j.lsp.product_storage.products.Food;

public interface Storage {
    public boolean addFood(Food food);
}
