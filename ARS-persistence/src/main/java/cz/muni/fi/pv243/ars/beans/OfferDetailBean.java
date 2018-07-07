package cz.muni.fi.pv243.ars.beans;

import cz.muni.fi.pv243.ars.persistence.model.Offer;
import cz.muni.fi.pv243.ars.persistence.model.Reservation;
import cz.muni.fi.pv243.ars.repository.OfferRepository;
import cz.muni.fi.pv243.ars.repository.ReservationRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class OfferDetailBean implements Serializable {

    @Inject
    private OfferRepository offerRepository;

    @Inject
    private ReservationRepository reservationRepository;

    private Long id;
    private Offer offer;

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

    public List<Reservation> getReservations() {
        return reservationRepository.findAllForOffer(offer);
    }
}
