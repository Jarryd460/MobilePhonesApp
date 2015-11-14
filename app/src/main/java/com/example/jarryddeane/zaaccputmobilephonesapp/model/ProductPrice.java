package com.example.jarryddeane.zaaccputmobilephonesapp.model;

import java.math.BigDecimal;

/**
 * Created by Jarryd Deane on 2015/11/14.
 */
public class ProductPrice {

    private Long id;
    private String dateFrom;
    private BigDecimal productPrice;

    public ProductPrice() {
    }

    public ProductPrice(String dateFrom, BigDecimal productPrice) {
        //this.id = id;
        this.dateFrom = dateFrom;
        this.productPrice = productPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }
}
