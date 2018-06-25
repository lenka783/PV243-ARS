package cz.muni.fi.pv243.ars.beans;

import cz.muni.fi.pv243.ars.controller.UserController;
import cz.muni.fi.pv243.ars.persistence.model.Address;
import cz.muni.fi.pv243.ars.persistence.model.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lenka smitalova on 6/19/18.
 */
@Named
@RequestScoped
public class UserInfoBean implements Serializable {

    private String name;
    private String surname;
    private String email;
    private Date dateOfBirth;

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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
