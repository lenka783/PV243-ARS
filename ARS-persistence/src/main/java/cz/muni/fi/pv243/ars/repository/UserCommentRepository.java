package cz.muni.fi.pv243.ars.repository;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.muni.fi.pv243.ars.persistence.model.Offer;
import cz.muni.fi.pv243.ars.persistence.model.UserComment;

/**
 * Created by jsmolar on 6/25/18.
 */
@Stateless
public class UserCommentRepository implements Serializable {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(UserComment userComment) {
        entityManager.persist(userComment);
        entityManager.flush();
    }

    public UserComment update(UserComment userComment) {
        userComment = entityManager.merge(userComment);
        entityManager.flush();
        return userComment;
    }

    public void delete(UserComment userComment) {
        entityManager.remove(entityManager.merge(userComment));
        entityManager.flush();
    }

    public UserComment findById(long id) {
        return entityManager.find(UserComment.class, id);
    }

    public List<UserComment> findAllForOffer(Offer offer) {
        return entityManager.createQuery("SELECT u FROM UserComment u where u.offer= :offer", UserComment.class)
            .setParameter("offer", offer)
            .getResultList();
    }

}
