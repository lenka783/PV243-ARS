package cz.muni.fi.pv243.ars.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Created by lenka smitalova on 6/18/18.
 */
@Named
@RequestScoped
public class AddressBean {

    private String street;
    private String city;
    private String state;
    private String country;
    private String postCode;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

}
