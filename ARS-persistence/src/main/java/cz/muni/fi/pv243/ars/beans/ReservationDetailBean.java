package cz.muni.fi.pv243.ars.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cz.muni.fi.pv243.ars.persistence.model.Reservation;
import cz.muni.fi.pv243.ars.repository.ReservationRepository;

/**
 * Created by jsmolar on 6/26/18.
 */
@Named
@RequestScoped
public class ReservationDetailBean {

    @Inject
    private ReservationRepository reservationRepository;

    private Reservation reservation;

    private long reservationId;

    @PostConstruct
    public void loadReservation() {
        reservation = reservationRepository.findById(reservationId);
    }

    public Reservation getReservation() {
        return reservation;
    }

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }
}
