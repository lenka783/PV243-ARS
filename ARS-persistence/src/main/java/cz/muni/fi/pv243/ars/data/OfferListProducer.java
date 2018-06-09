package cz.muni.fi.pv243.ars.data;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import cz.muni.fi.pv243.ars.persistence.model.Offer;
import cz.muni.fi.pv243.ars.repository.OfferRepository;

/**
 * Created by jsmolar on 6/7/18.
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

//    public void onOfferListChange()

    @PostConstruct
    public void getAvailableOffers() {
        offers = offerRepository.findAll();
    }

}
