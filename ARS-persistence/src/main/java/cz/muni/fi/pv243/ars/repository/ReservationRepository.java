package cz.muni.fi.pv243.ars.repository;

import cz.muni.fi.pv243.ars.persistence.model.Offer;
import cz.muni.fi.pv243.ars.persistence.model.Reservation;
import cz.muni.fi.pv243.ars.persistence.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Lenka Smitalova on 5/29/2018
 */
@Stateless
public class ReservationRepository implements Serializable {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Reservation reservation) {
        Offer offer = entityManager.merge(reservation.getOffer());
        offer.addReservation(reservation);
        User user = entityManager.merge(reservation.getUser());
        user.addReservation(reservation);
        entityManager.persist(reservation);
        entityManager.flush();
    }

    public Reservation update(Reservation reservation) {
        reservation = entityManager.merge(reservation);
        entityManager.flush();
        return reservation;
    }

    public void delete(Reservation reservation) {
        reservation = entityManager.merge(reservation);
        User tenant = reservation.getUser();
        if (tenant != null) {
            tenant.removeReservation(reservation);
        }
        Offer offer = reservation.getOffer();
        if (offer != null) {
            offer.removeReservation(reservation);
        }
        entityManager.remove(reservation);
        entityManager.flush();
    }

    public Reservation findById(Long id) {
        return entityManager.find(Reservation.class, id);
    }

    public List<Reservation> findAll() {
        return entityManager
                .createQuery("select r from Reservation r", Reservation.class)
                .getResultList();
    }

    public List<Reservation> findAllForUser(User user) {
        return entityManager
                .createQuery("select r from Reservation r where r.user.id = :user order by r.fromDate", Reservation.class)
                .setParameter("user", user.getId())
                .getResultList();
    }

    public List<Reservation> findAllForOffer(Offer offer) {
        return entityManager
                .createQuery("select r from Reservation r where r.offer.id = :offer order by r.fromDate", Reservation.class)
                .setParameter("offer", offer.getId())
                .getResultList();
    }
}
