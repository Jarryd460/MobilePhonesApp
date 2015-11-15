package com.example.jarryddeane.zaaccputmobilephonesapp.services;

import com.example.jarryddeane.zaaccputmobilephonesapp.model.OrderProduct;
import com.example.jarryddeane.zaaccputmobilephonesapp.model.Orders;

import java.util.List;

/**
 * Created by Jarryd Deane on 2015/11/15.
 */
public interface OrdersService extends Services<Orders, Long> {

    List<OrderProduct> getOrderProducts(Long id);

}
