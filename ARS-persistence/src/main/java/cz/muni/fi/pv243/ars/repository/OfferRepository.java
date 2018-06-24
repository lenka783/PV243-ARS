package cz.muni.fi.pv243.ars.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.muni.fi.pv243.ars.persistence.model.Offer;
import cz.muni.fi.pv243.ars.persistence.model.User;

/**
 * Created by mminatova on 5/29/18.
 */
@Stateless
public class OfferRepository implements Serializable {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private UserRepository userRepository;

    public void create(Offer offer) {
        entityManager.persist(offer);
        entityManager.flush();
    }

    public Offer update(Offer offer) {
        offer = entityManager.merge(offer);
        entityManager.flush();
        return offer;
    }

    public void delete(Offer offer) {
        entityManager.remove(entityManager.merge(offer));
        entityManager.flush();
    }

    public Offer findById(Long id) {
        Offer offer = entityManager.find(Offer.class, id);
        return offer;
    }

    public List<Offer> findAll() {
        List<Offer> offers = new ArrayList<>();
        offers.addAll(entityManager
                .createQuery("select o from Offer o", Offer.class)
                .getResultList());
        return offers;
    }

    public List<Offer> findAllForUser(User user) {
        List<Offer> offers = new ArrayList<>();
        offers.addAll(userRepository.findById(user.getId()).getOffers());
        return offers;
    }
}
