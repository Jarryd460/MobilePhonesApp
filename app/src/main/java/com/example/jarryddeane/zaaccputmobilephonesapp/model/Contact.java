package com.example.jarryddeane.zaaccputmobilephonesapp.model;

/**
 * Created by Jarryd Deane on 2015/11/14.
 */
public class Contact {

    private String homePhoneNumber;
    private String mobilePhoneNumber;

    public Contact() {}

    public Contact(String homePhoneNumber, String mobilePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public void setHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }
}
