package com.example.jarryddeane.zaaccputmobilephonesapp.services.impl;

import com.example.jarryddeane.zaaccputmobilephonesapp.model.ProductPrice;
import com.example.jarryddeane.zaaccputmobilephonesapp.repositories.RestAPI;
import com.example.jarryddeane.zaaccputmobilephonesapp.repositories.rest.RestProductPriceAPI;
import com.example.jarryddeane.zaaccputmobilephonesapp.services.ProductPriceService;
import com.example.jarryddeane.zaaccputmobilephonesapp.services.ProductService;

import java.util.List;

/**
 * Created by Jarryd Deane on 2015/11/15.
 */
public class ProductPriceServiceImpl implements ProductPriceService {

    final RestAPI<ProductPrice,Long> rest = new RestProductPriceAPI();

    @Override
    public ProductPrice findById(Long id) {
        return rest.get(id);
    }

    @Override
    public String save(ProductPrice entity) {
        return rest.post(entity);
    }

    @Override
    public String update(ProductPrice entity) {
        return rest.put(entity);
    }

    @Override
    public String delete(ProductPrice entity) {
        return rest.delete(entity);
    }

    @Override
    public List<ProductPrice> findAll() {
        return rest.getAll();
    }
}
