package cz.muni.fi.pv243.ars.service;

import java.time.LocalDate;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import cz.muni.fi.pv243.ars.persistence.model.UserComment;
import cz.muni.fi.pv243.ars.repository.OfferRepository;
import cz.muni.fi.pv243.ars.repository.UserCommentRepository;

/**
 * Created by jsmolar on 6/25/18.
 */
@Stateless
public class CreateUserComment {

    @Inject
    private Logger log;

    @Inject
    private UserCommentRepository userCommentRepository;

    @Inject
    private OfferRepository offerRepository;

    @Inject
    private Event<UserComment> userCommentEvent;

    public void create(Long offer_id, String message) {
        UserComment userComment = new UserComment();
        userComment.setOffer(offerRepository.findById(Long.valueOf(offer_id)))
            .setComment(message)
            .setCreationDate(LocalDate.now());
        userCommentRepository.create(userComment);

        log.info("User Comment for Offer: " + offer_id + " was created with message: " + message);

        userCommentEvent.fire(userComment);
    }

}
