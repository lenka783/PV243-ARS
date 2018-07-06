package cz.muni.fi.pv243.ars.beans;

import cz.muni.fi.pv243.ars.persistence.model.Offer;
import cz.muni.fi.pv243.ars.persistence.model.User;
import cz.muni.fi.pv243.ars.repository.OfferRepository;
import cz.muni.fi.pv243.ars.repository.UserRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by jsmolar on 6/7/18.
 */
@RequestScoped
@Named
public class OffersBean {

    @Inject
    private OfferRepository offerRepository;

    @Inject
    private UserRepository userRepository;

    private User user;
    private List<Offer> availableOffers;
    private List<Offer> usersOffers;

    public List<Offer> getAvailableOffers() {
        return availableOffers;
    }

    public List<Offer> getUsersOffers() {
        return usersOffers;
    }

    @PostConstruct
    public void loadOffers() {
        user = userController.matchUser();
        usersOffers = offerRepository.findAllForUser(user);
        availableOffers = offerRepository.findAllAvailableForUser(user);
    }
}
