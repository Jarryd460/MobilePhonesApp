package com.example.jarryddeane.zaaccputmobilephonesapp.services.impl;

import com.example.jarryddeane.zaaccputmobilephonesapp.model.Customer;
import com.example.jarryddeane.zaaccputmobilephonesapp.model.Orders;
import com.example.jarryddeane.zaaccputmobilephonesapp.repositories.RestAPI;
import com.example.jarryddeane.zaaccputmobilephonesapp.repositories.rest.RestCustomerAPI;
import com.example.jarryddeane.zaaccputmobilephonesapp.services.CustomerService;

import java.util.List;

/**
 * Created by Jarryd Deane on 2015/11/15.
 */
public class CustomerServiceImpl implements CustomerService {

    final RestAPI<Customer,Long> rest = new RestCustomerAPI();

    @Override
    public Customer findById(Long id) {
        return rest.get(id);
    }

    @Override
    public String save(Customer entity) {
        return rest.post(entity);
    }

    @Override
    public String update(Customer entity) {
        return rest.put(entity);
    }

    @Override
    public String delete(Customer entity) {
        return rest.delete(entity);
    }

    @Override
    public List<Customer> findAll() {
        return rest.getAll();
    }


    @Override
    public List<Orders> getOrders(Long id) {
        return null;
    }
}
