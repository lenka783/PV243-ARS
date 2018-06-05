package cz.muni.fi.pv243.ars.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import cz.muni.fi.pv243.ars.persistance.model.Offer;

/**
 * Created by mminatova on 5/29/18.
 */
@Stateless
public class OfferRepository {

    @Inject
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
