package com.example.jarryddeane.zaaccputmobilephonesapp.repositories.rest;

import com.example.jarryddeane.zaaccputmobilephonesapp.model.Orders;
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
public class RestOrdersAPI implements RestAPI<Orders,Long> {

    final String BASE_URL="http://mobilephones-jarryddeane.rhcloud.com/ord/";

    final HttpHeaders requestHeaders = RestMethods.getHeaders();
    final RestTemplate restTemplate = RestMethods.getRestTemplate();

    @Override
    public Orders get(Long id) {
        final String url = BASE_URL+"order/"+id.toString();
        HttpEntity<Orders> requestEntity = new HttpEntity<Orders>(requestHeaders);
        ResponseEntity<Orders> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Orders.class);
        Orders order = responseEntity.getBody();
        return order;
    }

    @Override
    public String post(Orders entity) {
        final String url = BASE_URL+"order/create";
        HttpEntity<Orders> requestEntity = new HttpEntity<Orders>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String put(Orders entity) {
        final String url = BASE_URL+"order/update/"+entity.getId().toString();
        HttpEntity<Orders> requestEntity = new HttpEntity<Orders>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String delete(Orders entity) {
        final String url = BASE_URL+"order/delete/"+entity.getId().toString();
        HttpEntity<Orders> requestEntity = new HttpEntity<Orders>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        return responseEntity.getBody();
    }

    @Override
    public List<Orders> getAll() {
        List<Orders> orders = new ArrayList<>();
        final String url = BASE_URL+"orders/";
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
        ResponseEntity<Orders[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Orders[].class);
        Orders[] results = responseEntity.getBody();

        for (Orders order : results) {
            orders.add(order);
        }
        return orders;
    }
}
