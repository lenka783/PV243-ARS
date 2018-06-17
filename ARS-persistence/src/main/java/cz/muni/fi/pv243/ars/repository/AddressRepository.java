package cz.muni.fi.pv243.ars.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.muni.fi.pv243.ars.persistence.model.Address;

/**
 * Created by jsmolar on 6/4/18.
 */
@Stateless
public class AddressRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Address address) {
        entityManager.persist(address);
        entityManager.flush();
    }

    public Address update(Address address) {
        address = entityManager.merge(address);
        entityManager.flush();
        return address;
    }

    public void delete(Address address) {
        entityManager.remove(entityManager.merge(address));
        entityManager.flush();
    }

    public Address findById(long id) {
        return entityManager.find(Address.class, id);
    }

    public List<Address> findAll() {
        return entityManager.createQuery("SELECT a FROM Address a", Address.class).getResultList();
    }

}
