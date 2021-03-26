package ru.job4j.isp.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<Item> items;

    public Menu() {
        this.items = new ArrayList<>();
    }

    public Menu addItem(Item item) {
        items.add(item);
        return this;
    }

    public void start() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean exit = false;
        while (!exit) {
            System.out.println("Меню:");
            for (Item item : items) {
                System.out.println(item.getName());
            }
            String menuItem = "";
            try {
                menuItem = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < items.size();) {
                String[] itemName = items.get(i).getName().split(" ");
                if (itemName[0].equals(menuItem)) {
                    System.out.println(items.get(i).execute()
                            .concat(System.lineSeparator()));
                    break;
                }
                i++;
                if (i == items.size()) {
                    System.out.println("Введён несуществующий пункт меню"
                            .concat(System.lineSeparator()));
                    break;
                }
            }
            String[] exitMenuItem = items.get(items.size() - 1).getName().split(" ");
            if (menuItem.equals(exitMenuItem[0])) {
                exit = true;
            }
        }
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.addItem(new Item("1 задача A", "Работает A"))
                .addItem(new Item("2 задача B", "Работает B"))
                .addItem(new Item("2.1 задача C", "Работает C"))
                .addItem(new Item("2.1.1 задача D", "Работает D"))
                .addItem(new Item("2.2 задача E", "Работает E"))
                .addItem(new Item("2.3 задача F", "Работает F"))
                .addItem(new Item("3 задача G", "Работает G"))
                .addItem(new Item("3.1 задача H", "Работает H"))
                .addItem(new Item("4 exit", "Завершение работы"));
        menu.start();
    }
}
