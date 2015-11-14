package com.example.jarryddeane.zaaccputmobilephonesapp.repositories.rest;

import com.example.jarryddeane.zaaccputmobilephonesapp.model.OrderProduct;
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
public class RestOrderProductAPI implements RestAPI<OrderProduct,Long> {

    final String BASE_URL="http://mobilephones-jarryddeane.rhcloud.com/ordpro/";

    final HttpHeaders requestHeaders = RestMethods.getHeaders();
    final RestTemplate restTemplate = RestMethods.getRestTemplate();

    @Override
    public OrderProduct get(Long id) {
        final String url = BASE_URL+"orderProduct/"+id.toString();
        HttpEntity<OrderProduct> requestEntity = new HttpEntity<OrderProduct>(requestHeaders);
        ResponseEntity<OrderProduct> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, OrderProduct.class);
        OrderProduct orderProduct = responseEntity.getBody();
        return orderProduct;
    }

    @Override
    public String post(OrderProduct entity) {
        final String url = BASE_URL+"orderProduct/create";
        HttpEntity<OrderProduct> requestEntity = new HttpEntity<OrderProduct>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String put(OrderProduct entity) {
        final String url = BASE_URL+"orderProduct/update/"+entity.getId().toString();
        HttpEntity<OrderProduct> requestEntity = new HttpEntity<OrderProduct>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String delete(OrderProduct entity) {
        final String url = BASE_URL+"orderProduct/delete/"+entity.getId().toString();
        HttpEntity<OrderProduct> requestEntity = new HttpEntity<OrderProduct>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        return responseEntity.getBody();
    }

    @Override
    public List<OrderProduct> getAll() {
        List<OrderProduct> orderProducts = new ArrayList<>();
        final String url = BASE_URL+"orderProducts/";
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
        ResponseEntity<OrderProduct[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, OrderProduct[].class);
        OrderProduct[] results = responseEntity.getBody();

        for (OrderProduct orderProduct : results) {
            orderProducts.add(orderProduct);
        }
        return orderProducts;
    }
}
