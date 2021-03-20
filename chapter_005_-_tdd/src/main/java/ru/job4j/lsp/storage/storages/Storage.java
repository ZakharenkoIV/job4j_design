package ru.job4j.lsp.storage.storages;

import ru.job4j.lsp.storage.products.Food;

public interface Storage {
    public boolean addFood(Food food);

    public boolean accept(Food food);
}
