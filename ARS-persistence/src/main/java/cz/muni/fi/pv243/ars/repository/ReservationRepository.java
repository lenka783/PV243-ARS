package cz.muni.fi.pv243.ars.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.muni.fi.pv243.ars.persistence.model.Offer;
import cz.muni.fi.pv243.ars.persistence.model.Reservation;
import cz.muni.fi.pv243.ars.persistence.model.User;

/**
 * Created by Lenka Smitalova on 5/29/2018
 */
@Stateless
public class ReservationRepository implements Serializable {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private UserRepository userRepository;

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
        User tenant = reservation.getUser();
        if (tenant != null) {
            tenant.removeReservation(reservation);
        }
        Offer offer = reservation.getOffer();
        if (offer != null) {
            offer.removeReservation(reservation);
        }
        entityManager.remove(entityManager.merge(reservation));
        entityManager.flush();
    }

    public Reservation findById(Long id) {
        Reservation reservation = entityManager.find(Reservation.class, id);
        return reservation;
    }

    public List<Reservation> findAll() {
        return entityManager
                .createQuery("select r from Reservation r", Reservation.class)
                .getResultList();
    }

    public List<Reservation> findAllForUser(User user) {
        //List<Reservation> result = new ArrayList<>();
        //result.addAll(userRepository.findById(user.getId()).getReservations());

        return entityManager
                .createQuery("select r from Reservation r where r.user.id = :user", Reservation.class)
                .setParameter("user", user.getId())
                .getResultList();
    }

    private int getAssignedId(Reservation reservation) {
        Offer offer = reservation.getOffer();
        return offer.hashCode();
    }
}
