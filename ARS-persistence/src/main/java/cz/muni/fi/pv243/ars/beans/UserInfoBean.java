package cz.muni.fi.pv243.ars.beans;

import java.time.ZoneId;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cz.muni.fi.pv243.ars.controller.UserController;
import cz.muni.fi.pv243.ars.persistence.model.User;

/**
 * Created by lenka smitalova on 6/19/18.
 */
@Named
@RequestScoped
public class UserInfoBean {

    @Inject
    private UserController userController;

    private User user;

    @PostConstruct
    public void findUser() {
        user = userController.matchUser();
    }

    public User getUser() {
        return user;
    }

    public Date getFormatedDateOfBirth() {
        return Date.from(user.getDateOfBirth().atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
