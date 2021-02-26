package ru.job4j.lsp.product_storage;

import ru.job4j.lsp.product_storage.products.Food;
import ru.job4j.lsp.product_storage.storages.Shop;
import ru.job4j.lsp.product_storage.storages.Storage;
import ru.job4j.lsp.product_storage.storages.Trash;
import ru.job4j.lsp.product_storage.storages.Warehouse;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ControlQuality {
    private Map<Predicate<Integer>, Consumer<Food>> conditionMap;
    private Map<String, Storage> storageMap;

    public ControlQuality() {
        this.storageMap = new HashMap<>();
        storageMap.put("Trash", new Trash());
        storageMap.put("Shop", new Shop());
        storageMap.put("Warehouse", new Warehouse());

        this.conditionMap = new HashMap<>();
        conditionMap.put(t -> t <= 0, food -> storageMap.get("Trash").addFood(food));
        conditionMap.put(t -> t > 0 && t < 25, food -> {
            food.setDiscount(5);
            storageMap.get("Shop").addFood(food);
        });
        conditionMap.put(t -> t >= 25 && t <= 75, food -> storageMap.get("Shop").addFood(food));
        conditionMap.put(t -> t > 75, food -> storageMap.get("Warehouse").addFood(food));
    }

    public boolean distribute(Food food) {
        boolean result = false;
        for (Predicate<Integer> pr : conditionMap.keySet()) {
            if (pr.test(freshness(food))) {
                conditionMap.get(pr).accept(food);
                result = true;
                break;
            }
        }
        return result;
    }

    private int freshness(Food food) {
        int msInDays = 86400000;
        int shelfLifeDays = (int) ((food.getExpiryDate().getTime() - food.getCreateDate().getTime()) / msInDays);
        int daysPassed = (int) ((System.currentTimeMillis() - food.getCreateDate().getTime()) / msInDays);
        return 100 - daysPassed * 100 / shelfLifeDays;
    }
}
