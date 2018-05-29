package cz.muni.fi.pv243.ars.manager.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.muni.fi.pv243.ars.manager.UserManager;
import cz.muni.fi.pv243.ars.model.User;

/**
 * Created by jsmolar on 5/28/18.
 */
@Stateless
public class UserManagerImpl extends AbstractManager<User> implements UserManager {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findById(Long id) {
        User user = entityManager.find(User.class, id);
        if (user == null){
            throw new IllegalArgumentException();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
}
