package com.shivali.postOffice.models;

public class Address {
    private String addressLine1;
    private String landmark;
    private String city;
    private String state;
    private int pinCode;

    Address(String addressLine1, String landmark, String city, String state, int pinCode) {
        this.addressLine1 = addressLine1;
        this.landmark = landmark;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
    }

    public int getPinCode() {
        return pinCode;
    }

    public String getState() {
        return state;
    }

    public boolean stateMatches(Address other) {
        return this.state.equals(other.state);
    }
}
