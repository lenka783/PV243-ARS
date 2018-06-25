package cz.muni.fi.pv243.ars.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import cz.muni.fi.pv243.ars.persistence.model.Address;

/**
 * Created by lenka smitalova on 6/18/18.
 */
@Named
@RequestScoped
public class AddressBean {

    public String getFriendlyLocation(Address address) {
        return address.getCity() + ", " + address.getCountry().toUpperCase();
    }

}
