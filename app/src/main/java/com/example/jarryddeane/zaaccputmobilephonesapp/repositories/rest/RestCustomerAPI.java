package com.example.jarryddeane.zaaccputmobilephonesapp.repositories.rest;

import com.example.jarryddeane.zaaccputmobilephonesapp.model.Customer;
import com.example.jarryddeane.zaaccputmobilephonesapp.repositories.RestAPI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jarryd Deane on 2015/11/15.
 */
public class RestCustomerAPI implements RestAPI<Customer,Long> {

    final String BASE_URL="http://mobilephones-jarryddeane.rhcloud.com/cus/";

    final HttpHeaders requestHeaders = RestMethods.getHeaders();
    final RestTemplate restTemplate = RestMethods.getRestTemplate();

    @Override
    public Customer get(Long id) {
        final String url = BASE_URL+"customer/"+id.toString();
        HttpEntity<Customer> requestEntity = new HttpEntity<Customer>(requestHeaders);
        ResponseEntity<Customer> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Customer.class);
        Customer customer = responseEntity.getBody();
        return customer;
    }

    @Override
    public String post(Customer entity) {
        final String url = BASE_URL+"customer/create";
        HttpEntity<Customer> requestEntity = new HttpEntity<Customer>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String put(Customer entity) {
        final String url = BASE_URL+"customer/update/"+entity.getId().toString();
        HttpEntity<Customer> requestEntity = new HttpEntity<Customer>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String delete(Customer entity) {
        final String url = BASE_URL+"customer/delete/"+entity.getId().toString();
        HttpEntity<Customer> requestEntity = new HttpEntity<Customer>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        return responseEntity.getBody();
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        final String url = BASE_URL+"customers/";
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
        ResponseEntity<Customer[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Customer[].class);
        Customer[] results = responseEntity.getBody();

        for (Customer customer : results) {
            customers.add(customer);
        }
        return customers;
    }

    //public String setPicture(Customer entity) {
        //final String url = BASE_URL+"/customer/setPicture/"+entity.getId().toString();
        //HttpEntity<InputStream> requestEntity = new HttpEntity<InputStream>(entity.getPicuteExtension(), entity.getPicture(), requestHeaders);
        //ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        //return responseEntity.getBody();
    //}

}
