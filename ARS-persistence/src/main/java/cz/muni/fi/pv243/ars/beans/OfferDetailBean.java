package cz.muni.fi.pv243.ars.beans;

import cz.muni.fi.pv243.ars.controller.UserController;
import cz.muni.fi.pv243.ars.persistence.model.Offer;
import cz.muni.fi.pv243.ars.persistence.model.Reservation;
import cz.muni.fi.pv243.ars.repository.OfferRepository;
import cz.muni.fi.pv243.ars.repository.ReservationRepository;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.time.ZoneId;
import java.util.Date;

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
