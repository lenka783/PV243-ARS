package cz.muni.fi.pv243.ars.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.muni.fi.pv243.ars.persistence.enumeration.UserRole;
import cz.muni.fi.pv243.ars.persistence.model.User;

/**
 * Created by jsmolar on 5/28/18.
 */
@Stateless
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    public User update(User user) {
        user = entityManager.merge(user);
        entityManager.flush();
        return user;
    }

    public void delete(User user) {
        entityManager.remove(entityManager.merge(user));
        entityManager.flush();
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
