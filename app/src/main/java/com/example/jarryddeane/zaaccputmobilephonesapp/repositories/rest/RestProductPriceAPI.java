package com.example.jarryddeane.zaaccputmobilephonesapp.repositories.rest;

import com.example.jarryddeane.zaaccputmobilephonesapp.model.ProductPrice;
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
public class RestProductPriceAPI implements RestAPI<ProductPrice,Long> {

    final String BASE_URL="http://mobilephones-jarryddeane.rhcloud.com/propri/";

    final HttpHeaders requestHeaders = RestMethods.getHeaders();
    final RestTemplate restTemplate = RestMethods.getRestTemplate();

    @Override
    public ProductPrice get(Long id) {
        final String url = BASE_URL+"productPrice/"+id.toString();
        HttpEntity<ProductPrice> requestEntity = new HttpEntity<ProductPrice>(requestHeaders);
        ResponseEntity<ProductPrice> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, ProductPrice.class);
        ProductPrice productPrice = responseEntity.getBody();
        return productPrice;
    }

    @Override
    public String post(ProductPrice entity) {
        final String url = BASE_URL+"productPrice/create";
        HttpEntity<ProductPrice> requestEntity = new HttpEntity<ProductPrice>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String put(ProductPrice entity) {
        final String url = BASE_URL+"productPrice/update/"+entity.getId().toString();
        HttpEntity<ProductPrice> requestEntity = new HttpEntity<ProductPrice>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String delete(ProductPrice entity) {
        final String url = BASE_URL+"productPrice/delete/"+entity.getId().toString();
        HttpEntity<ProductPrice> requestEntity = new HttpEntity<ProductPrice>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        return responseEntity.getBody();
    }

    @Override
    public List<ProductPrice> getAll() {
        List<ProductPrice> productPrices = new ArrayList<>();
        final String url = BASE_URL+"productPrices/";
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
        ResponseEntity<ProductPrice[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, ProductPrice[].class);
        ProductPrice[] results = responseEntity.getBody();

        for (ProductPrice productPrice : results) {
            productPrices.add(productPrice);
        }
        return productPrices;
    }
}
