package ru.job4j.lsp.storage.storages;

import java.util.List;

public class SimpleStoresList implements StoresList {

    private List<Storage> list;

    public SimpleStoresList(List<Storage> list) {
        this.list = list;
        list.add(new Trash());
        list.add(new Shop());
        list.add(new Warehouse());
    }

    @Override
    public List<Storage> getStorageList() {
        return list;
    }

    @Override
    public void addStorage(Storage storage) {
        list.add(storage);
    }
}
