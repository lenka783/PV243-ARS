package cz.muni.fi.pv243.ars.beans;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cz.muni.fi.pv243.ars.persistence.model.Address;
import cz.muni.fi.pv243.ars.repository.AddressRepository;

import java.util.*;

/**
 * Created by lenka smitalova on 6/18/18.
 */
@Named
@RequestScoped
public class AddressBean {

    private String city;
    private String country;

    public String getFriendlyLocation(Address address) {
        return address.getCity() + ", " + address.getCountry().toUpperCase();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof AddressBean)) return false;

        AddressBean that = (AddressBean) o;

        if (getCity() != null ? !getCity().equals(that.getCity()) : that.getCity() != null) return false;
        return getCountry() != null ? getCountry().equals(that.getCountry()) : that.getCountry() == null;
    }

    @Override
    public int hashCode() {
        int result = getCity() != null ? getCity().hashCode() : 0;
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        return result;
    }
}
