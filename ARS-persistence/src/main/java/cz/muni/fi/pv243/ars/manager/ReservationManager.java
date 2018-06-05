package cz.muni.fi.pv243.ars.manager;

import cz.muni.fi.pv243.ars.model.Reservation;

import java.util.List;

/**
 * Created by Lenka Smitalova on 5/29/2018
 */
public interface ReservationManager {

    Long create(Reservation reservation);

    Reservation update(Reservation reservation);

    void delete(Reservation reservation);

    Reservation findById(Long id);

    List<Reservation> findReservationsForGivenUser(Long userId);
}
