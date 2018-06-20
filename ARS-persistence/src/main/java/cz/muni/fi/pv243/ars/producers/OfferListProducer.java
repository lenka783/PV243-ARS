package cz.muni.fi.pv243.ars.producers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import cz.muni.fi.pv243.ars.persistence.model.Offer;
import cz.muni.fi.pv243.ars.repository.OfferRepository;

/**
 * Created by jsmolar on 6/19/18.
 */
@RequestScoped
public class OfferListProducer {

    @Inject
    private OfferRepository offerRepository;

    private List<Offer> offers;

    @Produces
    @Named
    public List<Offer> getOffers() {
        return offers;
    }

    public void onOfferListChange(@Observes(notifyObserver = Reception.IF_EXISTS) final Offer offer) {
        getOffers();
    }

    @PostConstruct
    public void getAllOffers() {
        offers = offerRepository.findAll();
    }

}
