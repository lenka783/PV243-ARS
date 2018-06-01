package cz.muni.fi.pv243.data.reservation;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import cz.muni.fi.pv243.ars.model.Reservation;
import cz.muni.fi.pv243.ars.model.User;
import cz.muni.fi.pv243.ars.repository.ReservationRepository;

/**
 * Created by jsmolar on 6/4/18.
 */
@RequestScoped
public class ReservationListProducer {

    @Inject
    private ReservationRepository reservationRepository;

    private List<Reservation> reservations;

    @Produces
    @Named
    public List<Reservation> getReservations() {
        return reservations;
    }

    @PostConstruct
    public void findReservationsForUser(User user) {
        reservations = reservationRepository.findAllForUser(user);
    }

}
