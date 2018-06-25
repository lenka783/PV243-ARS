package cz.muni.fi.pv243.ars.repository;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.muni.fi.pv243.ars.beans.UserInfoBean;
import cz.muni.fi.pv243.ars.persistence.enumeration.UserRole;
import cz.muni.fi.pv243.ars.persistence.model.User;

/**
 * Created by jsmolar on 5/28/18.
 */
@Stateless
public class UserRepository implements Serializable {

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

    public User findByKCid(String keycloakPrincipal) {
        List<User> result =  entityManager.createQuery(
            "SELECT u FROM User u where u.keycloakPrincipal= :keycloakPrincipal", User.class)
            .setParameter("keycloakPrincipal", keycloakPrincipal)
            .getResultList();

        if(!result.isEmpty()) {
            return result.get(0);
        }

        return null;
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
