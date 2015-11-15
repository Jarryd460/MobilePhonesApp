package com.example.jarryddeane.zaaccputmobilephonesapp.services.impl;

import com.example.jarryddeane.zaaccputmobilephonesapp.model.Product;
import com.example.jarryddeane.zaaccputmobilephonesapp.repositories.RestAPI;
import com.example.jarryddeane.zaaccputmobilephonesapp.repositories.rest.RestProductAPI;
import com.example.jarryddeane.zaaccputmobilephonesapp.services.ProductService;

import java.util.List;

/**
 * Created by Jarryd Deane on 2015/11/15.
 */
public class ProductServiceImpl implements ProductService {

    final RestAPI<Product,Long> rest = new RestProductAPI();

    @Override
    public Product findById(Long id) {
        return rest.get(id);
    }

    @Override
    public String save(Product entity) {
        return rest.post(entity);
    }

    @Override
    public String update(Product entity) {
        return rest.put(entity);
    }

    @Override
    public String delete(Product entity) {
        return rest.delete(entity);
    }

    @Override
    public List<Product> findAll() {
        return rest.getAll();
    }
}
