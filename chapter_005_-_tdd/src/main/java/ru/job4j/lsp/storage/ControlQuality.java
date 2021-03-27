package ru.job4j.lsp.storage;

import ru.job4j.lsp.storage.products.Food;
import ru.job4j.lsp.storage.storages.Storage;
import ru.job4j.lsp.storage.storages.StoresList;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private StoresList list;

    public ControlQuality(StoresList list) {
        this.list = list;
    }

    public boolean distribute(Food food) {
        boolean result = false;
        for (Storage storage : list.getStorageList()) {
            if (storage.accept(food)) {
                result = storage.addFood(food);
                break;
            }
        }
        return result;
    }

    private void resort() {
        for (Food food : getAllFoods()) {
            distribute(food);
        }
    }

    private List<Food> getAllFoods() {
        List<Food> foodsList = new ArrayList<>();
        for (Storage storage : list.getStorageList()) {
            foodsList.addAll(storage.clean());
        }
        return foodsList;
    }
}