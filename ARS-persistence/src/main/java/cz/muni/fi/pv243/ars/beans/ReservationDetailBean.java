package cz.muni.fi.pv243.ars.beans;

import cz.muni.fi.pv243.ars.controller.UserController;
import cz.muni.fi.pv243.ars.persistence.model.Reservation;
import cz.muni.fi.pv243.ars.repository.ReservationRepository;

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
public class ReservationDetailBean implements Serializable {

    @Inject
    private ReservationRepository reservationRepository;

    private Long id;
    private Reservation reservation;
    
    public void loadReservation() {
        reservation = reservationRepository.findById(id);
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void deleteReservation() throws IOException {
        try {
            loadReservation();
            reservationRepository.delete(reservation);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("Something went wrong, please try again later.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("reservations.jsf");
    }
}
