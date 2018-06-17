package cz.muni.fi.pv243.ars.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.muni.fi.pv243.ars.persistence.model.Offer;
import cz.muni.fi.pv243.ars.persistence.model.User;

/**
 * Created by mminatova on 5/29/18.
 */
@Stateless
public class OfferRepository {

    @PersistenceContext
    private EntityManager entityManager;

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
        return entityManager.createQuery("SELECT o FROM Offer o", Offer.class).getResultList();
    }

    public List<Offer> findAllForUser(User user) {
        return entityManager.createQuery("SELECT o FROM Offer o where o.user= :user", Offer.class)
                .setParameter("user", user)
                .getResultList();
    }
}
