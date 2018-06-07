package cz.muni.fi.pv243.ars.repository;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.muni.fi.pv243.ars.persistence.model.Reservation;
import cz.muni.fi.pv243.ars.persistence.model.User;

/**
 * Created by Lenka Smitalova on 5/29/2018
 */
@Stateless
public class ReservationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private UserRepository userRepository;

    public void create(Reservation reservation) {
        entityManager.persist(reservation);
        entityManager.flush();
    }

    public Reservation update(Reservation reservation) {
        reservation = entityManager.merge(reservation);
        entityManager.flush();
        return reservation;
    }

    public void delete(Reservation reservation) {
        User host = reservation.getHost();
        host.removeReservation(reservation);
        entityManager.remove(reservation);
        entityManager.flush();
    }

    public Reservation findById(Long id) {
        Reservation reservation = entityManager.find(Reservation.class, id);
        return reservation;
    }

    public List<Reservation> findAllForUser(User user) {
        List<Reservation> result = new ArrayList<>();
        result.addAll(userRepository.findById(user.getId()).getReservations());

        return result;
    }

}
