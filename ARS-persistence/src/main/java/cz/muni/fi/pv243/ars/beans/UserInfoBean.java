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
@SessionScoped
public class UserInfoBean implements Serializable {

    @ManagedProperty(value = "UserController")
    private UserController userController;

    private User currentUser;

    @PostConstruct
    public void init() {
        currentUser = userController.matchUser();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
