package cz.muni.fi.pv243.ars.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import cz.muni.fi.pv243.ars.persistance.model.Address;

/**
 * Created by jsmolar on 6/4/18.
 */
@Stateless
public class AddressRepository {

    @Inject
    private EntityManager entityManager;

    public void create(Address address) {
        entityManager.persist(address);
    }

    public Address update(Address address) {
        return entityManager.merge(address);
    }

    public void delete(Address address) {
        entityManager.remove(address);
    }

    public Address findById(long id) {
        return entityManager.find(Address.class, id);
    }

    public List<Address> findAll() {
        return entityManager.createQuery("SELECT a FROM Address a", Address.class).getResultList();
    }

}
