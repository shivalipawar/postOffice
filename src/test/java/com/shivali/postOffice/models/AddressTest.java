package com.shivali.postOffice.models;


import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AddressTest {
    @Test
    public void testMatchingState() {
        Address address1 = new Address("H-1", "blah", "NW", "MH", 123);
        Address address2 = new Address("H-1", "blah", "NW", "MH", 123);
        assertTrue(address1.stateMatches(address2));
    }

    @Test
    public void testNotMatchingState() {
        Address address1 = new Address("H-1", "blah", "NW", "MH", 123);
        Address address2 = new Address("H-1", "blah", "NW", "KA", 123);
        assertFalse(address1.stateMatches(address2));
    }
}