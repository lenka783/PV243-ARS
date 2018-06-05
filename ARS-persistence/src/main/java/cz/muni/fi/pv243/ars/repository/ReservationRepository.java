package cz.muni.fi.pv243.ars.repository;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import cz.muni.fi.pv243.ars.persistance.model.Reservation;
import cz.muni.fi.pv243.ars.persistance.model.User;

/**
 * Created by Lenka Smitalova on 5/29/2018
 */
@Stateless
public class ReservationRepository {

    @Inject
    private EntityManager entityManager;

    @Inject
    private UserRepository userRepository;

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

    public List<Reservation> findAllForUser(User user) {
        List<Reservation> result = new ArrayList<>();
        result.addAll(userRepository.findById(user.getId()).getReservations());

        return result;
    }

}
