package cz.muni.fi.pv243.ars.manager;

import java.util.List;

/**
 * Created by jsmolar on 5/25/18.
 */
public interface Manager<T> {
    void create(T entity);
    void delete(T entity);
    void edit(T entity);
    T findById(Long id);
    List<T> getAll();
}
