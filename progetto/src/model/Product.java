package model;

import java.io.Serializable;

public class Product implements Serializable {
    private int code;
    private String name;
    private int quantity;

    public Product(int code, String name, int quantity) {
        this.code = code;
        this.name = name;
        this.quantity = quantity;
    }

    public int getCode() {
        return this.code;
    }
}
