package ru.job4j.lsp.product_storage.products;

import java.util.Date;

public class Milk extends Food {
    public Milk(String name, Date expiryDate, Date createDate, double price, int discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
