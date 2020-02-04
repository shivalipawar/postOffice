package com.shivali.postOffice.services;

import com.shivali.postOffice.exceptions.InvalidStampException;
import com.shivali.postOffice.exceptions.PostOfficeNotInProvinceException;
import com.shivali.postOffice.models.PostCard;
import com.shivali.postOffice.models.PostOffice;
import com.shivali.postOffice.models.Stamp;

import java.util.List;
import java.util.Optional;

public class PostService {

    List<PostOffice> postOffices;

    public PostService(List<PostOffice> postOffices) {
        this.postOffices = postOffices;
    }

    public void submit(PostCard postCard) {
        if (!isValidStamp(postCard)) {
            throw new InvalidStampException();
        }
        Optional<PostOffice> postOffice = getMatchingPostOffice(postCard);
        if (!postOffice.isPresent()) {
            throw new PostOfficeNotInProvinceException();
        }
        postOffice.get().deliver(postCard);
    }

    private Optional<PostOffice> getMatchingPostOffice(PostCard postCard) {
        return postOffices.stream()
                .filter(p -> p.isUnderProvince(postCard.getRecipientAddress()))
                .findFirst();
    }

    private boolean isValidStamp(PostCard postCard) {
        return postCard.getStamp() == Stamp.NATIONAL ||
                postCard.getRecipientAddress().stateMatches(postCard.getSender().getAddress());
    }
}
