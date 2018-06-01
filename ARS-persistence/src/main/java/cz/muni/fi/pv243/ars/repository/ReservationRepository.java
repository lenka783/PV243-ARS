package cz.muni.fi.pv243.ars.repository;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import cz.muni.fi.pv243.ars.model.Reservation;
import cz.muni.fi.pv243.ars.model.User;

/**
 * Created by Lenka Smitalova on 5/29/2018
 */
@Stateless
public class ReservationRepository {

    @Inject
    private EntityManager entityManager;

    @Inject
    private UserRepository userRepository;

    public Reservation findById(Long id) {
        Reservation reservation = entityManager.find(Reservation.class, id);
        if (reservation == null) {
            throw new IllegalArgumentException();
        }
        return reservation;
    }

    public List<Reservation> findAll() {
        return entityManager
                .createQuery("SELECT r FROM Reservation r", Reservation.class)
                .getResultList();
    }

    public List<Reservation> findAllForUser(User user) {
        List<Reservation> result = new ArrayList<>();
        result.addAll(userRepository.findById(user.getId()).getReservations());

        return result;
    }

}
