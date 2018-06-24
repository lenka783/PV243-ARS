package cz.muni.fi.pv243.ars.beans;

import cz.muni.fi.pv243.ars.persistence.model.Reservation;
import cz.muni.fi.pv243.ars.repository.ReservationRepository;
import cz.muni.fi.pv243.ars.repository.UserRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * Created by jsmolar on 6/4/18.
 */
@SessionScoped
@Named
public class ReservationsPageBean implements Serializable {

    @Inject
    private ReservationRepository reservationRepository;

    @Inject
    private UserRepository userRepository;

    private List<Reservation> reservations;
    private long selectedId;
    private Reservation selectedReservation;
    private Date checkIn;
    private Date checkOut;

    public List<Reservation> getReservations() {
        return reservations;
    }

    @PostConstruct
    public void getUserReservations() {
        reservations = reservationRepository.findAllForUser(userRepository.findById(0l));
    }

    public void loadReservation() {
        selectedReservation = reservationRepository.findById(selectedId);
        checkIn = Date.from(selectedReservation.getFromDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        checkOut = Date.from(selectedReservation.getToDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public Reservation getSelectedReservation() {
        return selectedReservation;
    }

    public long getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(long selectedId) {
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

    public String deleteSelectedReservation() {
        try {
            reservationRepository.delete(selectedReservation);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("Something went wrong, please try again later.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
        return "reservations";
    }

}
