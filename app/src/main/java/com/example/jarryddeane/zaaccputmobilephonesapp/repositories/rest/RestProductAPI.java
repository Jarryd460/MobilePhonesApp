package com.example.jarryddeane.zaaccputmobilephonesapp.repositories.rest;

import com.example.jarryddeane.zaaccputmobilephonesapp.model.Product;
import com.example.jarryddeane.zaaccputmobilephonesapp.repositories.RestAPI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jarryd Deane on 2015/11/15.
 */
public class RestProductAPI implements RestAPI<Product,Long> {

    final String BASE_URL="http://mobilephones-jarryddeane.rhcloud.com/pro/";

    final HttpHeaders requestHeaders = RestMethods.getHeaders();
    final RestTemplate restTemplate = RestMethods.getRestTemplate();

    @Override
    public Product get(Long id) {
        final String url = BASE_URL+"product/"+id.toString();
        HttpEntity<Product> requestEntity = new HttpEntity<Product>(requestHeaders);
        ResponseEntity<Product> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Product.class);
        Product product = responseEntity.getBody();
        return product;
    }

    @Override
    public String post(Product entity) {
        final String url = BASE_URL+"product/create";
        HttpEntity<Product> requestEntity = new HttpEntity<Product>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String put(Product entity) {
        final String url = BASE_URL+"product/update/"+entity.getId().toString();
        HttpEntity<Product> requestEntity = new HttpEntity<Product>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String delete(Product entity) {
        final String url = BASE_URL+"product/delete/"+entity.getId().toString();
        HttpEntity<Product> requestEntity = new HttpEntity<Product>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        return responseEntity.getBody();
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        final String url = BASE_URL+"products/";
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
        ResponseEntity<Product[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Product[].class);
        Product[] results = responseEntity.getBody();

        for (Product product : results) {
            products.add(product);
        }
        return products;
    }
}
