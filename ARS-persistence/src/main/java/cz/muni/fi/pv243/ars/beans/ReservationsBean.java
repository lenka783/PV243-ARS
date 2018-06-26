package cz.muni.fi.pv243.ars.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import cz.muni.fi.pv243.ars.controller.UserController;
import cz.muni.fi.pv243.ars.persistence.model.Reservation;
import cz.muni.fi.pv243.ars.persistence.model.User;
import cz.muni.fi.pv243.ars.repository.ReservationRepository;

/**
 * Created by jsmolar on 6/4/18.
 */
@Named
@RequestScoped
public class ReservationsBean {

    @Inject
    private UserController userController;

    @Inject
    private ReservationRepository reservationRepository;

    private User user;

    private List<Reservation> reservations;
    private Long selectedId;
    private Reservation selectedReservation;
    private Date checkIn;
    private Date checkOut;

    public List<Reservation> getReservations() {
        return reservations;
    }

    @PostConstruct
    public void findUser() {
        user = userController.matchUser();
        reservations = reservationRepository.findAllForUser(user);
    }

    public void loadReservation() {
        selectedReservation = reservationRepository.findById(selectedId);
        checkIn = Date.from(selectedReservation.getFromDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        checkOut = Date.from(selectedReservation.getToDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public Reservation getSelectedReservation() {
        return selectedReservation;
    }

    public Long getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(Long selectedId) {
        this.selectedId = selectedId;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public String updateSelectedReservation() {
        try {
            selectedReservation.setFromDate(checkOut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            selectedReservation.setToDate(checkIn.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            reservationRepository.update(selectedReservation);
        } catch (Exception e ) {
            FacesMessage msg = new FacesMessage("Something went wrong while updating reservation.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return null;
    }

    public void deleteSelectedReservation() throws IOException {
        try {
            loadReservation();
            reservationRepository.delete(selectedReservation);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("Something went wrong, please try again later.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("reservations.jsf");
    }

}
