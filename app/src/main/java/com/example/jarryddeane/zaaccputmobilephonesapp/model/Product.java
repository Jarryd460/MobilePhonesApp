package com.example.jarryddeane.zaaccputmobilephonesapp.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Jarryd Deane on 2015/11/14.
 */
public class Product {

    private Long id;
    private String name;
    private String manufacturer;
    private BigDecimal price;
    private String operatingSystem;
    private String screenSize;
    private String touchScreen;
    private String camera;
    private String memory;
    private List<OrderProduct> orderProductList;
    private List<ProductPrice> productPriceList;
    private String pictureExtension;
    private byte[] picture;

    public Product() {}

    public Product(String name, String manufacturer, BigDecimal price, String operatingSystem, String screenSize, String touchScreen, String camera, String memory, List<OrderProduct> orderProductList, List<ProductPrice> productPriceList, String pictureExtension, byte[] picture) {
        //this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.operatingSystem = operatingSystem;
        this.screenSize = screenSize;
        this.touchScreen = touchScreen;
        this.camera = camera;
        this.memory = memory;
        this.orderProductList = orderProductList;
        this.productPriceList = productPriceList;
        this.pictureExtension = pictureExtension;
        this.picture = picture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public String getTouchScreen() {
        return touchScreen;
    }

    public void setTouchScreen(String touchScreen) {
        this.touchScreen = touchScreen;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public List<OrderProduct> getOrderProductList() {
        return orderProductList;
    }

    public void setOrderProductList(List<OrderProduct> orderProductList) {
        this.orderProductList = orderProductList;
    }

    public List<ProductPrice> getProductPriceList() {
        return productPriceList;
    }

    public void setProductPriceList(List<ProductPrice> productPriceList) {
        this.productPriceList = productPriceList;
    }

    public String getPictureExtension() {
        return pictureExtension;
    }

    public void setPictureExtension(String pictureExtension) {
        this.pictureExtension = pictureExtension;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
