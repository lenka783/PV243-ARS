package cz.muni.fi.pv243.ars.repository;

import cz.muni.fi.pv243.ars.persistence.model.Offer;
import cz.muni.fi.pv243.ars.persistence.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by mminatova on 5/29/18.
 */
@Stateless
public class OfferRepository implements Serializable {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Offer offer) {
        User user = entityManager.merge(offer.getUser());
        user.addOffer(offer);
        entityManager.persist(offer);
        entityManager.flush();
    }

    public Offer update(Offer offer) {
        offer = entityManager.merge(offer);
        entityManager.flush();
        return offer;
    }

    public void delete(Offer offer) {
        User host = offer.getUser();
        if (host != null) {
            host.removeOffer(offer);
        }
        entityManager.remove(entityManager.merge(offer));
        entityManager.flush();
    }

    public Offer findById(Long id) {
        Offer offer = entityManager.find(Offer.class, id);
        return offer;
    }

    public List<Offer> findAll() {
        List<Offer> offers = entityManager
                .createQuery("select o from Offer o", Offer.class)
                .getResultList();
        System.out.println("all offers found: " + offers.size());
        return offers;
    }

    public List<Offer> findAllAvailableForUser(User user) {
        if (user == null || user.getId() == null) {
            return findAll();
        }
        List<Offer> offers = entityManager
                .createQuery("select o from Offer o where o.user.id < :user_id or o.user.id > :user_id", Offer.class)
                .setParameter("user_id", user.getId())
                .getResultList();
        System.out.println("available offers found: " + offers.size());
        return offers;
    }

    public List<Offer> findAllForUser(User user) {
        List<Offer> offers = entityManager
                .createQuery("select o from Offer o where o.user.id = :user_id", Offer.class)
                .setParameter("user_id", user.getId())
                .getResultList();
        System.out.println("user's offers found: " + offers.size());
        return offers;
    }
}
