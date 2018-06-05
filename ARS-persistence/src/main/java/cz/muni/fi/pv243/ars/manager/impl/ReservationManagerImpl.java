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
public class ReservationManagerImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public Long create(Reservation reservation) {
        entityManager.persist(reservation);
        return reservation.getId();
    }

    public Reservation update(Reservation reservation) {
        return entityManager.merge(reservation);
    }

    public void delete(Reservation reservation) {
        entityManager.remove(reservation);
    }

    public Reservation findById(Long id) {
        Reservation reservation = entityManager.find(Reservation.class, id);
        return reservation;
    }

    public List<Reservation> findReservationsForGivenUser(Long userId) {
        List<Reservation> reservations = entityManager
                .createQuery("select r from Reservation r join r.user u where u.id = :userId")
                .setParameter("userId", userId)
                .getResultList();
        return reservations;
    }

}
