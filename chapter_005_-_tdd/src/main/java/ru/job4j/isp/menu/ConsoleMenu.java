package ru.job4j.isp.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleMenu implements Menu {
    private Actions actions;

    public ConsoleMenu(Actions actions) {
        this.actions = actions;
    }

    @Override
    public void start() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean exit = false;
        while (!exit) {
            System.out.println("Меню:");
            for (Item item : actions.getItems()) {
                System.out.println(item.getName());
            }
            String menuItem = "";
            try {
                menuItem = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < actions.getItems().size(); ) {
                String[] itemName = actions.getItems().get(i).getName().split(" ");
                if (itemName[0].equals(menuItem)) {
                    System.out.println(actions.getItems().get(i).execute()
                            .concat(System.lineSeparator()));
                    break;
                }
                i++;
                if (i == actions.getItems().size()) {
                    System.out.println("Введён несуществующий пункт меню"
                            .concat(System.lineSeparator()));
                    break;
                }
            }
            String[] exitMenuItem = actions.getItems()
                    .get(actions.getItems().size() - 1).getName().split(" ");
            if (menuItem.equals(exitMenuItem[0])) {
                exit = true;
            }
        }
    }

    public static void main(String[] args) {
        Menu menu = new ConsoleMenu(new AlphabetActions());
        menu.start();
    }
}
