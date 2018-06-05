package cz.muni.fi.pv243.ars.manager.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.muni.fi.pv243.ars.manager.OfferManager;
import cz.muni.fi.pv243.ars.model.Offer;

/**
 * Created by mminatova on 5/29/18.
 */
public class OfferManagerImpl implements OfferManager {

    @PersistenceContext
    private EntityManager entityManager;

    public Long create(Offer offer) {
        entityManager.persist(offer);
        return offer.getId();
    }

    public Offer findById(Long id) {
        Offer offer = entityManager.find(Offer.class, id);
        if (offer == null){
            throw new IllegalArgumentException();
        }
        return offer;
    }

    public List<Offer> getAll() {
        return entityManager.createQuery("SELECT o FROM Offer o", Offer.class).getResultList();
    }

}
