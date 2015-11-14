package com.example.jarryddeane.zaaccputmobilephonesapp.model;

/**
 * Created by Jarryd Deane on 2015/11/14.
 */
public class OrderProduct {

    private Long id;
    private int quantity;

    public OrderProduct() {}

    public OrderProduct(int quantity) {
        //this.id = id;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
