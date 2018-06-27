package cz.muni.fi.pv243.ars.beans;

import java.io.IOException;
import java.time.ZoneId;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import cz.muni.fi.pv243.ars.controller.UserController;
import cz.muni.fi.pv243.ars.persistence.model.Offer;
import cz.muni.fi.pv243.ars.persistence.model.Reservation;
import cz.muni.fi.pv243.ars.repository.ReservationRepository;
import cz.muni.fi.pv243.ars.repository.UserRepository;

@RequestScoped
@Named
public class ReservationBean {

    @Inject
    private ReservationRepository reservationRepository;

    @Inject
    private UserController userController;

    @Inject
    private ReservationDetailBean reservationDetailBean;

    private Date checkInDate;
    private Date checkOutDate;
    private Integer numberOfPeople;

    @PostConstruct
    public void reservationDatesInit() {
        checkInDate = Date.from(reservationDetailBean.getReservation().getFromDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        checkOutDate = Date.from(reservationDetailBean.getReservation().getToDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        numberOfPeople = reservationDetailBean.getReservation().getNumberOfPeople();
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public void create(Offer offer) throws IOException {
        if(!userController.isLoggedIn() ) {
            userController.logIn();
        }

        String redirect;

        try {
            Reservation reservation = new Reservation();
            reservation.setFromDate(checkInDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            reservation.setToDate(checkOutDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            reservation.setNumberOfPeople(numberOfPeople);
            reservation.setOffer(offer);
            reservation.setUser(userController.matchUser());
            reservationRepository.create(reservation);

            redirect = "reservations.jsf?for_user=true";
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("Something went wrong, please try again later.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

            redirect = "index.jsf?for_user=false";
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(redirect);
    }

    public String updateReservation(Reservation reservation) {
        try {
            reservation.setFromDate(checkInDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            reservation.setToDate(checkOutDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            System.out.println(checkOutDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            System.out.println(reservation.toString());
            reservationRepository.update(reservation);
        } catch (Exception e ) {
            FacesMessage msg = new FacesMessage("Something went wrong while updating reservation.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return null;
    }

    public void deleteReservation(Reservation reservation) throws IOException {
        try {
            reservationRepository.delete(reservation);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("Something went wrong, please try again later.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("reservations.jsf");
    }
}
