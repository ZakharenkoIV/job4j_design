package ru.job4j.lsp.storage.products;

import java.util.Date;

public class Sandwich extends Food {
    public Sandwich(String name, Date expiryDate, Date createDate, double price, int discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}