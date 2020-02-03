package com.shivali.postOffice.models;

public class PostCard {
    private Address recipientAddress;
    private User sender;
    private String recipientName;
    private long recipientContact;
    private Stamp stamp;

    public PostCard(Address recipientAddress, User sender, String recipientName, long recipientContact, Stamp stamp) {
        this.recipientAddress = recipientAddress;
        this.sender = sender;
        this.recipientName = recipientName;
        this.recipientContact = recipientContact;
        this.stamp = stamp;
    }


    public Address getRecipientAddress() {
        return recipientAddress;
    }

    public User getSender() {
        return sender;
    }

    public Stamp getStamp() {
        return stamp;
    }
}
