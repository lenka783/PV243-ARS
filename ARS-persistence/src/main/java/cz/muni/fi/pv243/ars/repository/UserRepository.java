package cz.muni.fi.pv243.ars.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.muni.fi.pv243.ars.persistance.enumeration.UserRole;
import cz.muni.fi.pv243.ars.persistance.model.User;

/**
 * Created by jsmolar on 5/28/18.
 */
@Stateless
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(User user) {
        entityManager.persist(user);
    }

    public User update(User user) {
        return entityManager.merge(user);
    }

    public void delete(User user) {
        entityManager.remove(user);
    }

    public User findById(long id) {
        return entityManager.find(User.class, id);
    }

    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public List<User> findAllForRole(UserRole role) {
        return entityManager.createQuery("SELECT u FROM User u where u.role= :role", User.class)
            .setParameter("role", role)
            .getResultList();
    }

}
