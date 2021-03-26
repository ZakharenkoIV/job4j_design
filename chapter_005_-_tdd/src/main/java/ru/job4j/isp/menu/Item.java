package ru.job4j.isp.menu;

public class Item {
    private String name;
    private String text;

    public Item(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public String execute() {
        return text;
    }
}
