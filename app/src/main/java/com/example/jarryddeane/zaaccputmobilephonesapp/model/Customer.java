package com.example.jarryddeane.zaaccputmobilephonesapp.model;

import java.util.List;

/**
 * Created by Jarryd Deane on 2015/11/14.
 */
public class Customer {

    private Long id;
    private Name name;
    private String sex;
    private String dateOfBirth;
    private Contact contact;
    private Address address;
    private List<Orders> orderList;
    private Login login;
    private String isAdmin;
    private byte[] picture;

    public Customer() {}

    public Customer(Name name, String sex, String dateOfBirth, Contact contact, Address address, List<Orders> orderList, Login login, String isAdmin, byte[] picture) {
        //this.id = id;
        this.name = name;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.contact = contact;
        this.address = address;
        this.orderList = orderList;
        this.login = login;
        this.isAdmin = isAdmin;
        this.picture = picture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Orders> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Orders> orderList) {
        this.orderList = orderList;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

}
