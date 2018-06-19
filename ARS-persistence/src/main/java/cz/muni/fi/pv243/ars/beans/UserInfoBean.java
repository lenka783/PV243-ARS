package cz.muni.fi.pv243.ars.beans;

import cz.muni.fi.pv243.ars.persistence.model.Address;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;
import java.util.Date;

/**
 * Created by lenka smitalova on 6/19/18.
 */
@Named
@RequestScoped
public class UserInfoBean {

    private String name = "Admin";
    private String surname = "Admin";
    private String email = "admin@ars.cz";
    private String password = "admin";
    private Date dateOfBirth = new Date();

    @ManagedProperty(value = "#{AddressBean}")
    private AddressBean address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
