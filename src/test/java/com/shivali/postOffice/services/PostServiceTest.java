package com.shivali.postOffice.services;

import com.shivali.postOffice.exceptions.InvalidStampException;
import com.shivali.postOffice.exceptions.PostOfficeNotInProvinceException;
import com.shivali.postOffice.models.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class PostServiceTest {
    PostService postService;
    PostOffice postOffice1;
    PostOffice postOffice2;
    PostCard postCardWithLocalStamp;
    PostCard postCardWithNationalStamp;
    PostCard postCardWithLocalStampAndNationalAddress;
    Address senderAddress, recieverAddress;

    @Before
    public void initialize() {
        postOffice1 = Mockito.mock(PostOffice.class);
        postOffice2 = Mockito.mock(PostOffice.class);
        senderAddress = Mockito.mock(Address.class);
        recieverAddress = Mockito.mock(Address.class);
        postCardWithLocalStamp = getPostCardWithLocalStamp();
        postCardWithNationalStamp = getPostCardWithNationalStamp();
        postService = new PostService(Arrays.asList(postOffice1, postOffice2));
    }

    @Test()
    public void sendSucceedsIfDestinationExistsForLocalStamp() {
        when(postOffice1.isUnderProvince(recieverAddress)).thenReturn(true);
        boolean isSent = postService.send(postCardWithLocalStamp);
        assertTrue(isSent);
        verify(postOffice1).deliver(postCardWithLocalStamp);
        verifyZeroInteractions(postOffice2);
    }

    @Test
    public void sendSucceedsIfDestinationExistsForNationalStamp() {
        when(postOffice1.isUnderProvince(recieverAddress)).thenReturn(true);
        boolean isSent = postService.send(postCardWithNationalStamp);
        assertTrue(isSent);
        verify(postOffice1).deliver(postCardWithNationalStamp);
        verifyZeroInteractions(postOffice2);
    }

    @Test
    public void sendSucceedsIfDestinationAddressIsLocalWithNationalStamp() {
        when(postOffice1.isUnderProvince(recieverAddress)).thenReturn(true);
        boolean isSent = postService.send(postCardWithNationalStamp);
        assertTrue(isSent);
        verify(postOffice1).deliver(postCardWithNationalStamp);
        verifyZeroInteractions(postOffice2);
    }


    @Test(expected = PostOfficeNotInProvinceException.class)
    public void sendFailsIfDestinationIsNotInServiceArea() {
        postService.send(postCardWithNationalStamp);
    }

    @Test(expected = InvalidStampException.class)
    public void sendFailsIfDestinationAddressIsNationalWithLocalStamp() {
        postCardWithLocalStampAndNationalAddress = getPostCardWithLocalStampAndNationalAddress();

        when(postOffice1.isUnderProvince(recieverAddress)).thenReturn(true);

        postService.send(postCardWithLocalStampAndNationalAddress);
    }


    private PostCard getPostCardWithLocalStampAndNationalAddress() {
        when(recieverAddress.stateMatches(senderAddress)).thenReturn(false);

        return new PostCard(recieverAddress,
                new User("Harry", "h@g.co", senderAddress),
                "John",
                91753081,
                Stamp.LOCAL);
    }

    private PostCard getPostCardWithLocalStamp() {
        when(recieverAddress.stateMatches(senderAddress)).thenReturn(true);
        return new PostCard(recieverAddress, new User("Harry", "h@g.co", senderAddress), "John", 91753081, Stamp.LOCAL);
    }

    private PostCard getPostCardWithNationalStamp() {
        return new PostCard(recieverAddress, new User("Harry", "h@g.co", senderAddress), "John", 91753081, Stamp.NATIONAL);
    }
}