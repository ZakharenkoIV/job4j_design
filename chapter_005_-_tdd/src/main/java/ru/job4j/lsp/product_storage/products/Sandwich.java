package ru.job4j.lsp.product_storage.products;

import javax.xml.crypto.Data;

public class Sandwich extends Food {
    public Sandwich(String name, Data expiryDate, Data createDate, double price, int discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}