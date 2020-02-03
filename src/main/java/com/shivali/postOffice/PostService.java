package com.shivali.postOffice;

import com.shivali.postOffice.models.PostCard;
import com.shivali.postOffice.models.PostOffice;
import com.shivali.postOffice.models.Stamp;

import java.util.List;
import java.util.Optional;

class PostService {

    List<PostOffice> postOffices;

    PostService(List<PostOffice> postOffices) {
        this.postOffices = postOffices;
    }

    boolean send(PostCard postCard) {
        if (!isValidStamp(postCard)) {
            throw new InvalidStampException("Cannot send postcard to other state without national stamp");
        }
        Optional<PostOffice> postOffice = postOffices.stream()
                .filter(p -> p.isUnderProvince(postCard.getRecipientAddress()))
                .findFirst();

        if (!postOffice.isPresent()) {
            throw new PostOfficeNotInProvinceException("Destination post office is not in province");
        } else {
            postOffice.get().deliver(postCard);
            return true;
        }
    }

    private boolean isValidStamp(PostCard postCard) {
        return postCard.getStamp() == Stamp.NATIONAL ||
                postCard.getRecipientAddress().stateMatches(postCard.getSender().getAddress());
    }
}
