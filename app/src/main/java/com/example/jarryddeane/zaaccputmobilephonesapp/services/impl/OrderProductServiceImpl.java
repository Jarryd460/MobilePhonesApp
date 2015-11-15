package com.example.jarryddeane.zaaccputmobilephonesapp.services.impl;

import com.example.jarryddeane.zaaccputmobilephonesapp.model.OrderProduct;
import com.example.jarryddeane.zaaccputmobilephonesapp.repositories.RestAPI;
import com.example.jarryddeane.zaaccputmobilephonesapp.repositories.rest.RestOrderProductAPI;
import com.example.jarryddeane.zaaccputmobilephonesapp.services.OrderProductService;

import java.util.List;

/**
 * Created by Jarryd Deane on 2015/11/15.
 */
public class OrderProductServiceImpl implements OrderProductService {

    final RestAPI<OrderProduct,Long> rest = new RestOrderProductAPI();

    @Override
    public OrderProduct findById(Long id) {
        return rest.get(id);
    }

    @Override
    public String save(OrderProduct entity) {
        return rest.post(entity);
    }

    @Override
    public String update(OrderProduct entity) {
        return rest.put(entity);
    }

    @Override
    public String delete(OrderProduct entity) {
        return rest.delete(entity);
    }

    @Override
    public List<OrderProduct> findAll() {
        return rest.getAll();
    }
}
