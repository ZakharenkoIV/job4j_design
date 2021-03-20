package ru.job4j.lsp.storage.products;

import java.util.Date;

public class Bread extends Food {
    public Bread(String name, Date expiryDate, Date createDate, double price, int discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
