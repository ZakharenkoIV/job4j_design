package ru.job4j.lsp.storage.storages;

import ru.job4j.lsp.storage.products.Food;

import java.util.List;

public interface Storage {
    List<Food> getFoods();

    boolean addFood(Food food);

    boolean accept(Food food);

    List<Food> clean();
}
