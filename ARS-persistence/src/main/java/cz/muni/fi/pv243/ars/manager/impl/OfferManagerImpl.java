package cz.muni.fi.pv243.ars.manager.impl;

import cz.muni.fi.pv243.ars.manager.OfferManager;
import cz.muni.fi.pv243.ars.model.Offer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by mminatova on 5/29/18.
 */
public class OfferManagerImpl extends AbstractManager<Offer> implements OfferManager {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Offer findById(Long id) {
        Offer offer = entityManager.find(Offer.class, id);
        if (offer == null){
            throw new IllegalArgumentException();
        }
        return offer;
    }


    @Override
    public List<Offer> getAll() {
        return entityManager.createQuery("SELECT o FROM Offer o", Offer.class).getResultList();
    }
}
