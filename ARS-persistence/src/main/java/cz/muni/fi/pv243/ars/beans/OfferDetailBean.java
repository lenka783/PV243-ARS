package cz.muni.fi.pv243.ars.beans;

import cz.muni.fi.pv243.ars.persistence.model.Offer;
import cz.muni.fi.pv243.ars.repository.OfferRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named
public class OfferDetailBean implements Serializable {

    @Inject
    private OfferRepository offerRepository;

    private Long id;
    private Offer offer;
    private boolean forUser;

    public void loadOffer() {
        offer = offerRepository.findById(id);
    }

    public Offer getOffer() {
        return offer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isForUser() {
        return forUser;
    }

    public void setForUser(boolean forUser) {
        this.forUser = forUser;
    }
}
