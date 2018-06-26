package cz.muni.fi.pv243.ars.beans;

import cz.muni.fi.pv243.ars.persistence.model.Offer;
import cz.muni.fi.pv243.ars.persistence.model.User;
import cz.muni.fi.pv243.ars.repository.OfferRepository;
import cz.muni.fi.pv243.ars.repository.UserRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by jsmolar on 6/7/18.
 */
@SessionScoped
@Named
public class OffersBean implements Serializable {

    @Inject
    private OfferRepository offerRepository;

    @Inject
    private UserRepository userRepository;

    private List<Offer> offers;
    private Long selectedId;
    private Long currentUserId;
    private boolean forUser;
    private boolean loaded;
    private Offer selectedOffer;

    @Produces
    public List<Offer> getOffers() {
        return offers;
    }

    public void loadOffers() {
        User user = currentUserId != null ? userRepository.findById(currentUserId) : null;
        if (forUser) {
            offers = offerRepository.findAllForUser(user);
        } else {
            offers = offerRepository.findAllAvailableForUser(user);
        }
    }



    public void loadOffer() {
        selectedOffer = offerRepository.findById(selectedId);
    }

    public Offer getSelectedOffer() {
        return selectedOffer;
    }

    public Long getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(Long selectedId) {
        this.selectedId = selectedId;
    }

    public Long getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(Long currentUserId) {
        this.currentUserId = currentUserId;
    }

    public boolean isForUser() {
        return forUser;
    }

    public void setForUser(boolean forUser) {
        this.forUser = forUser;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }
}
