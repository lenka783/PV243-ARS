package cz.muni.fi.pv243.ars.manager.impl;

import cz.muni.fi.pv243.ars.manager.ReservationManager;
import cz.muni.fi.pv243.ars.model.Reservation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Lenka Smitalova on 5/29/2018
 */

public class ReservationManagerImpl implements ReservationManager {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Reservation findById(Long id) {
        Reservation reservation = entityManager.find(Reservation.class, id);
        if (reservation == null) {
            throw new IllegalArgumentException();
        }
        return reservation;
    }

    @Override
    public List<Reservation> getAll() {
        return entityManager
                .createQuery("SELECT r FROM Reservation r", Reservation.class)
                .getResultList();
    }
}
