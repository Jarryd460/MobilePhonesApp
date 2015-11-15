package com.example.jarryddeane.zaaccputmobilephonesapp.services.impl;

import com.example.jarryddeane.zaaccputmobilephonesapp.model.OrderProduct;
import com.example.jarryddeane.zaaccputmobilephonesapp.model.Orders;
import com.example.jarryddeane.zaaccputmobilephonesapp.repositories.RestAPI;
import com.example.jarryddeane.zaaccputmobilephonesapp.repositories.rest.RestOrdersAPI;
import com.example.jarryddeane.zaaccputmobilephonesapp.services.OrdersService;

import java.util.List;

/**
 * Created by Jarryd Deane on 2015/11/15.
 */
public class OrdersServiceImpl implements OrdersService {

    final RestAPI<Orders,Long> rest = new RestOrdersAPI();

    @Override
    public Orders findById(Long id) {
        return rest.get(id);
    }

    @Override
    public String save(Orders entity) {
        return rest.post(entity);
    }

    @Override
    public String update(Orders entity) {
        return rest.put(entity);
    }

    @Override
    public String delete(Orders entity) {
        return rest.delete(entity);
    }

    @Override
    public List<Orders> findAll() {
        return rest.getAll();
    }

    @Override
    public List<OrderProduct> getOrderProducts(Long id) {
        return null;
    }
}
