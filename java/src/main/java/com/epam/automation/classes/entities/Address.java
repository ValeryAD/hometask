package com.epam.automation.classes.entities;

public class Address {

    private String city;
    private String street;
    private String houseNum;
    private String apartment;
    private long phoneNumber;

    public Address() {
    }

    public Address(String city, String street, String houseNumber, String apartment, long phoneNumber) {
        this.city = city;
        this.street = street;
        this.houseNum = houseNumber;
        this.apartment = apartment;
        this.phoneNumber = phoneNumber;

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
