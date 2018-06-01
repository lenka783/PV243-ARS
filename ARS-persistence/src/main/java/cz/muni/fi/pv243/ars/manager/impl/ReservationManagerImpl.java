package cz.muni.fi.pv243.ars.manager.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.muni.fi.pv243.ars.manager.ReservationManager;
import cz.muni.fi.pv243.ars.model.Reservation;

/**
 * Created by Lenka Smitalova on 5/29/2018
 */
@Stateless
public class ReservationManagerImpl implements ReservationManager {

    @PersistenceContext
    private EntityManager entityManager;

    public Reservation findById(Long id) {
        Reservation reservation = entityManager.find(Reservation.class, id);
        if (reservation == null) {
            throw new IllegalArgumentException();
        }
        return reservation;
    }

    public List<Reservation> getAll() {
        return entityManager
                .createQuery("SELECT r FROM Reservation r", Reservation.class)
                .getResultList();
    }
}
