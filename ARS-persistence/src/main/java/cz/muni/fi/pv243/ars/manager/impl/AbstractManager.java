package cz.muni.fi.pv243.ars.manager.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.muni.fi.pv243.ars.manager.Manager;

/**
 * Created by jsmolar on 5/28/18.
 */
public abstract class AbstractManager<T> implements Manager<T> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException();
        }
        entityManager.persist(entity);
    }

    @Override
    public void delete(T entity) {
        if(entity == null){
            throw new IllegalArgumentException();
        }
        entityManager.remove(entity);
    }

    @Override
    public void edit(T entity) {
        if(entity == null){
            throw new IllegalArgumentException();
        }
        entityManager.merge(entity);
    }

    @Override
    abstract public T findById(Long id);

    @Override
    abstract public List<T> getAll();
}
