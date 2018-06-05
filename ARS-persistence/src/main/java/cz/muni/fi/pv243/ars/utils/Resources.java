package cz.muni.fi.pv243.ars.utils;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by jsmolar on 6/4/18.
 */
public class Resources {

    @Produces
    @PersistenceContext
    private EntityManager em;

}
