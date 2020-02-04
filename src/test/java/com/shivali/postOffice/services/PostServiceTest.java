package com.shivali.postOffice.services;

import com.shivali.postOffice.exceptions.InvalidStampException;
import com.shivali.postOffice.exceptions.PostOfficeNotInProvinceException;
import com.shivali.postOffice.models.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.mockito.Mockito.*;

public class PostServiceTest {
    PostService postService;
    PostOffice postOffice1;
    PostOffice postOffice2;
    PostCard postCardWithLocalStamp;
    PostCard postCardWithNationalStamp;
    PostCard postCardWithLocalStampAndNationalAddress;
    Address senderAddress, receiverAddress;

    @Before
    public void initialize() {
        postOffice1 = mock(PostOffice.class);
        postOffice2 = mock(PostOffice.class);
        senderAddress = mock(Address.class);
        receiverAddress = mock(Address.class);
        postCardWithLocalStamp = postCardWithLocalStamp();
        postCardWithNationalStamp = postCardWithNationalStamp();
        postService = new PostService(Arrays.asList(postOffice1, postOffice2));
    }

    @Test()
    public void submitSucceedsIfDestinationExistsForLocalStamp() {
        when(postOffice1.isUnderProvince(receiverAddress)).thenReturn(true);
        postService.submit(postCardWithLocalStamp);
        verify(postOffice1).deliver(postCardWithLocalStamp);
        verifyZeroInteractions(postOffice2);
    }

    @Test
    public void submitSucceedsIfDestinationExistsForNationalStamp() {
        when(postOffice1.isUnderProvince(receiverAddress)).thenReturn(true);
        postService.submit(postCardWithNationalStamp);
        verify(postOffice1).deliver(postCardWithNationalStamp);
        verifyZeroInteractions(postOffice2);
    }

    @Test
    public void submitSucceedsIfDestinationAddressIsLocalWithNationalStamp() {
        when(postOffice1.isUnderProvince(receiverAddress)).thenReturn(true);
        postService.submit(postCardWithNationalStamp);
        verify(postOffice1).deliver(postCardWithNationalStamp);
        verifyZeroInteractions(postOffice2);
    }


    @Test(expected = PostOfficeNotInProvinceException.class)
    public void submitFailsIfDestinationIsNotInServiceArea() {
        postService.submit(postCardWithNationalStamp);
    }

    @Test(expected = InvalidStampException.class)
    public void submitFailsIfDestinationAddressIsNationalWithLocalStamp() {
        postCardWithLocalStampAndNationalAddress = postCardWithLocalStampAndNationalAddress();

        when(postOffice1.isUnderProvince(receiverAddress)).thenReturn(true);

        postService.submit(postCardWithLocalStampAndNationalAddress);
    }


    private PostCard postCardWithLocalStampAndNationalAddress() {
        when(receiverAddress.stateMatches(senderAddress)).thenReturn(false);

        return new PostCard(receiverAddress,
                new User("Harry", "h@g.co", senderAddress),
                "John",
                91753081,
                Stamp.LOCAL, "");
    }

    private PostCard postCardWithLocalStamp() {
        when(receiverAddress.stateMatches(senderAddress)).thenReturn(true);
        return new PostCard(receiverAddress,
                new User("Harry", "h@g.co", senderAddress),
                "John",
                91753081,
                Stamp.LOCAL, "");
    }

    private PostCard postCardWithNationalStamp() {
        return new PostCard(receiverAddress,
                new User("Harry", "h@g.co", senderAddress),
                "John",
                91753081,
                Stamp.NATIONAL, "");
    }
}