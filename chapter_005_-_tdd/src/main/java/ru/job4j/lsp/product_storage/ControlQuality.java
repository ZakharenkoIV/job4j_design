package ru.job4j.lsp.product_storage;

import ru.job4j.lsp.product_storage.products.Food;
import ru.job4j.lsp.product_storage.storages.Shop;
import ru.job4j.lsp.product_storage.storages.Storage;
import ru.job4j.lsp.product_storage.storages.Trash;
import ru.job4j.lsp.product_storage.storages.Warehouse;

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