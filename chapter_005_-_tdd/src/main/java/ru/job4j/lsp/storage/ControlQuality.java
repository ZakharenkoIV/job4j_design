package ru.job4j.lsp.storage;

import ru.job4j.lsp.storage.products.Food;
import ru.job4j.lsp.storage.storages.Shop;
import ru.job4j.lsp.storage.storages.Storage;
import ru.job4j.lsp.storage.storages.Trash;
import ru.job4j.lsp.storage.storages.Warehouse;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private List<Storage> storageList;

    public ControlQuality() {
        this.storageList = new ArrayList<>();
        storageList.add(new Trash());
        storageList.add(new Shop());
        storageList.add(new Warehouse());
    }

    public boolean distribute(Food food) {
        boolean result = false;
        for (Storage storage : storageList) {
            if (storage.accept(food)) {
                result = storage.addFood(food);
                break;
            }
        }
        return result;
    }
}