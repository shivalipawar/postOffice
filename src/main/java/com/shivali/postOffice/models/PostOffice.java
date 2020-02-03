package com.shivali.postOffice.models;

import java.util.Set;

public class PostOffice {
    private int id;
    private String name;
    private Address address;
    private Set<Integer> province;

    public PostOffice(int id, String name, Address address, Set<Integer> province) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.province = province;
    }

    public void recieve(PostCard postCard) {
        System.out.println("Recieved postcard"+postCard);
    }

    public boolean isUnderProvince(Address address) {
        return province.contains(address.getPinCode());
    }
}
