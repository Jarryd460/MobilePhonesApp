package com.example.jarryddeane.zaaccputmobilephonesapp.services;

import com.example.jarryddeane.zaaccputmobilephonesapp.model.Customer;
import com.example.jarryddeane.zaaccputmobilephonesapp.model.Orders;

import java.util.List;

/**
 * Created by Jarryd Deane on 2015/11/15.
 */
public interface CustomerService extends Services<Customer, Long> {

    List<Orders> getOrders(Long id);

}
