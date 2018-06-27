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

    public List<Reservation> getReservations() {
        return reservations;
    }

    @PostConstruct
    public void findUser() {
        user = userController.matchUser();
        reservations = reservationRepository.findAllForUser(user);
    }

}
