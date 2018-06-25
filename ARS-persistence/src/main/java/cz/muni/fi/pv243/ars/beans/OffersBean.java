package cz.muni.fi.pv243.ars.beans;

import cz.muni.fi.pv243.ars.persistence.model.Offer;
import cz.muni.fi.pv243.ars.persistence.model.User;
import cz.muni.fi.pv243.ars.repository.OfferRepository;
import cz.muni.fi.pv243.ars.repository.UserRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
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
    private long selectedId;
    private Long userId;
    private Offer selectedOffer;

    public List<Offer> getOffers() {
        System.out.println("User id: " + userId);
        return offers;
    }

    @PostConstruct
    public void getAvailableOffers() {
        offers = offerRepository.findAll();
    }

    public void loadOffer() {
        selectedOffer = offerRepository.findById(selectedId);
    }

    public Offer getSelectedOffer() {
        return selectedOffer;
    }

    public long getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(long selectedId) {
        this.selectedId = selectedId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
