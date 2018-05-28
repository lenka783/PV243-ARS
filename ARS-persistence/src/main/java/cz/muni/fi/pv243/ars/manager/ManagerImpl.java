package cz.muni.fi.pv243.ars.manager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by jsmolar on 5/28/18.
 */
public abstract class ManagerImpl<T> implements Manager<T> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Parameter entity can't be null!");
        }
        entityManager.persist(entity);
    }

    @Override
    public void delete(T entity) {
        if(entity == null){
            throw new IllegalArgumentException("Parameter entity can't be null!");
        }
        entityManager.remove(entity);
    }

    @Override
    public void edit(T entity) {
        if(entity == null){
            throw new IllegalArgumentException("Parameter entity can't be null!");
        }
        entityManager.merge(entity);
    }

    @Override
    abstract public T findById(Long id);

    @Override
    abstract public List<T> getAll();
}
