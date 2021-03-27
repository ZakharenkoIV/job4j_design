package ru.job4j.isp.menu;

import java.util.ArrayList;
import java.util.List;

public class AlphabetActions implements Actions {
    List<Item> items;

    public AlphabetActions() {
        this.items = new ArrayList<>();
        items.add(new Item("1 задача A", "Работает A"));
        items.add(new Item("2 задача B", "Работает B"));
        items.add(new Item("2.1 задача C", "Работает C"));
        items.add(new Item("2.1.1 задача D", "Работает D"));
        items.add(new Item("2.2 задача E", "Работает E"));
        items.add(new Item("2.3 задача F", "Работает F"));
        items.add(new Item("3 задача G", "Работает G"));
        items.add(new Item("3.1 задача H", "Работает H"));
        items.add(new Item("4 exit", "Завершение работы"));
    }

    @Override
    public List<Item> getItems() {
        return items;
    }
}
