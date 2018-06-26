package cz.muni.fi.pv243.ars.controller;

import java.io.IOException;
import java.io.Serializable;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import cz.muni.fi.pv243.ars.beans.UserInfoBean;
import cz.muni.fi.pv243.ars.persistence.enumeration.UserRole;
import cz.muni.fi.pv243.ars.persistence.model.User;
import cz.muni.fi.pv243.ars.repository.UserRepository;
import org.keycloak.KeycloakPrincipal;

/**
 * Created by jsmolar on 6/4/18.
 */
@Named
@RequestScoped
public class UserController{

    @Inject
    private Logger log;

    @Inject
    private UserRepository userRepository;

    @Inject
    private HttpServletRequest request;

    private UserInfoBean currentUser;

    public boolean isLoggedIn() {
        return request.getUserPrincipal() != null;
    }

    public boolean isHost() {
        if (!isLoggedIn()) {
            return false;
        }
        User user = matchUser();
        return user.getRoles().contains(UserRole.HOST);
    }

    public boolean isTenant() {
        if (!isLoggedIn()) {
            return true;
        }
        User user = matchUser();
        System.out.println("User is tenant: " + user.getRoles().contains(UserRole.TENANT));
        return user.getRoles().contains(UserRole.TENANT);
    }

    public void logOut() throws ServletException, IOException {
        request.logout();

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("index.jsf");
    }

    public void logIn() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("login.jsf?faces-redirect=true");
    }

    public User matchUser() {
        KeycloakPrincipal keycloakPrincipal = (KeycloakPrincipal) request.getUserPrincipal();

        if(keycloakPrincipal == null) {
            log.info("User and Keycloak Principal wants to be matched, but Principal is null");
            return null;
        }
        log.info("Principal: " + keycloakPrincipal);
        log.info("Principal name: " + keycloakPrincipal.getName());

        User user = userRepository.findByKCid(keycloakPrincipal.getName());
        log.info("User and KC Principal are matched. User email: " + user.getEmail());

        return user;
    }

    public String getUserName() {
        User user = matchUser();

        return user.getName();
    }

    public String getUserSurname() {
        User user = matchUser();

        return user.getSurname();
    }

    public String getUserEmail() {
        User user = matchUser();

        return user.getEmail();
    }

    public Date getUserDateOfBirth() {
        User user = matchUser();

        return Date.from(user.getDateOfBirth().atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public Long getId() {
        User user = matchUser();

        return user == null ? null : user.getId();
    }
}
