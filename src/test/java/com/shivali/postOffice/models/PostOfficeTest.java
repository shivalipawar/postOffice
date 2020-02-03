package com.shivali.postOffice.models;


import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PostOfficeTest {

    private PostOffice postOffice;

    @Before
    public void initialize() {
        Set<Integer> province = new HashSet<>();
        province.add(414);
        province.add(415);
        postOffice = new PostOffice(1, "Abc", null, province);
    }

    @Test
    public void isUnderProvince() {
        Address address = new Address("", "", "", "", 414);
        assertTrue(postOffice.isUnderProvince(address));
    }

    @Test
    public void isUnderProvinceShouldFailIfPincodeNotSupported() {
        Address address = new Address("", "", "", "", 417);
        assertFalse(postOffice.isUnderProvince(address));
    }
}