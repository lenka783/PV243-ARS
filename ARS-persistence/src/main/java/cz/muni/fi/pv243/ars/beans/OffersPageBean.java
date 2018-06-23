package cz.muni.fi.pv243.ars.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;

import cz.muni.fi.pv243.ars.persistence.model.Offer;
import cz.muni.fi.pv243.ars.repository.OfferRepository;

/**
 * Created by jsmolar on 6/7/18.
 */
@SessionScoped
@Named
public class OffersPageBean implements Serializable {

    @Inject
    private OfferRepository offerRepository;

    @ManagedProperty(value = "#{OfferFilterBean}")
    private OfferFilterBean offerFilterBean;

    private List<Offer> offers;
    private long selectedId;
    private Offer selectedOffer;

    public List<Offer> getOffers() {
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
}
