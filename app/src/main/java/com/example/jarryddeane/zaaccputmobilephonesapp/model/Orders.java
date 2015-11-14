package com.example.jarryddeane.zaaccputmobilephonesapp.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Jarryd Deane on 2015/11/14.
 */
public class Orders {

    private Long id;
    private String orderStatus;
    private String dateOrderPlaced;
    private String dateOrderPaid;
    private BigDecimal totalOrderPrice;
    private List<OrderProduct> orderProductList;

    public Orders() {}

    public Orders(String orderStatus, String dateOrderPlaced, String dateOrderPaid, BigDecimal totalOrderPrice, List<OrderProduct> orderProductList) {
        //this.id = id;
        this.orderStatus = orderStatus;
        this.dateOrderPlaced = dateOrderPlaced;
        this.dateOrderPaid = dateOrderPaid;
        this.totalOrderPrice = totalOrderPrice;
        this.orderProductList = orderProductList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getDateOrderPlaced() {
        return dateOrderPlaced;
    }

    public void setDateOrderPlaced(String dateOrderPlaced) {
        this.dateOrderPlaced = dateOrderPlaced;
    }

    public String getDateOrderPaid() {
        return dateOrderPaid;
    }

    public void setDateOrderPaid(String dateOrderPaid) {
        this.dateOrderPaid = dateOrderPaid;
    }

    public BigDecimal getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public void setTotalOrderPrice(BigDecimal totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
    }

    public List<OrderProduct> getOrderProductList() {
        return orderProductList;
    }

    public void setOrderProductList(List<OrderProduct> orderProductList) {
        this.orderProductList = orderProductList;
    }
}
