package com.shivali.postOffice;

import com.shivali.postOffice.models.*;
import com.shivali.postOffice.services.PostService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class App {
    public static void main(String[] args) {
        User sender = getSender();
        PostCard postCard = getPostCard(sender, getRecipientAddress(), "Hello Bro");
        PostService postService = getPostService();
        postService.submit(postCard);
    }

    private static User getSender() {
        Address senderAddress = new Address("Oasis 400", "Amul Parlour", "Bhopal", "Madhya Pradesh", 412301);
        return new User("John", "john@gmail.com,", senderAddress);
    }

    private static PostService getPostService() {
        List<PostOffice> postOffices = getPostOffices();
        return new PostService(postOffices);
    }

    private static PostCard getPostCard(User sender, Address recipientAddress, String body) {
        return new PostCard(recipientAddress, sender, "Samuel", 87883519, Stamp.NATIONAL, body);
    }

    private static Address getRecipientAddress() {
        return new Address("Buliding 10", "Vodafone Store", "Pune", "Maharashtra", 411021);
    }

    private static List<PostOffice> getPostOffices() {
        Address postOfficeAddress = new Address("A-202, Buliding 1", "Reliance Mart", "Pune", "Maharashtra", 411021);
        HashSet<Integer> province = new HashSet<>();
        province.add(411021);
        province.add(410122);
        List<PostOffice> postOffices = new ArrayList<>();
        PostOffice postOffice = new PostOffice(101, "Pune", postOfficeAddress, province);
        postOffices.add(postOffice);
        return postOffices;
    }
}
