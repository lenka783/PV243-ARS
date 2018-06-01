package cz.muni.fi.pv243.ars.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import cz.muni.fi.pv243.ars.model.Offer;

/**
 * Created by mminatova on 5/29/18.
 */
public class OfferRepository {

    @Inject
    private EntityManager entityManager;

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
