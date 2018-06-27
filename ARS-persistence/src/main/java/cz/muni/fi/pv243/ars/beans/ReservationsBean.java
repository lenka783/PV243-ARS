package cz.muni.fi.pv243.ars.beans;

import cz.muni.fi.pv243.ars.controller.UserController;
import cz.muni.fi.pv243.ars.persistence.model.Reservation;
import cz.muni.fi.pv243.ars.persistence.model.User;
import cz.muni.fi.pv243.ars.repository.ReservationRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

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
