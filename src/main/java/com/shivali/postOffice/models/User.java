package com.shivali.postOffice.models;

public class User {
    private String name;
    private String email;
    private Address address;

    public User(String name, String email, Address address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }
}
