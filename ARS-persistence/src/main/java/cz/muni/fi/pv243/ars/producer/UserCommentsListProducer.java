package cz.muni.fi.pv243.ars.producer;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import cz.muni.fi.pv243.ars.persistence.model.Offer;
import cz.muni.fi.pv243.ars.persistence.model.UserComment;
import cz.muni.fi.pv243.ars.repository.OfferRepository;
import cz.muni.fi.pv243.ars.repository.UserCommentRepository;

/**
 * Created by jsmolar on 6/25/18.
 */
@Named
@RequestScoped
public class UserCommentsListProducer implements Serializable {

    @Inject
    private UserCommentRepository userCommentRepository;

    @Inject
    private OfferRepository offerRepository;

    private String userComments = "";

    private long offerId;

    @Produces
    public String getUserComments() {
        return userComments;
    }

    public void onUserCommentChanged(@Observes final UserComment userComment) {
        userComments += userComment.getComment() + "\n";
    }

    public void findAllUserCommentsForOffer() {
        Offer selectedOffer = offerRepository.findById(offerId);
        List<UserComment> comments = userCommentRepository.findAllForOffer(selectedOffer);

        if(!comments.isEmpty()) {
            for(UserComment comment : comments) {
                userComments += comment.getComment() + "\n";
            }
        }
    }

    public long getOfferId() {
        return offerId;
    }

    public void setOfferId(long offerId) {
        this.offerId = offerId;
    }
}
