package cz.muni.fi.pv243.ars.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import cz.muni.fi.pv243.ars.persistence.model.User;
import cz.muni.fi.pv243.ars.repository.UserRepository;
import org.keycloak.KeycloakPrincipal;

/**
 * Created by jsmolar on 6/4/18.
 */
@Named
@RequestScoped
public class UserController {

    @Inject
    private Logger log;

    @Inject
    private UserRepository userRepository;

    @Inject
    private HttpServletRequest request;

    public boolean isLoggedIn() {
        return request.getUserPrincipal() == null;
    }

    public void logOut() throws ServletException, IOException {
        request.logout();
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
        log.info("User and KC Principal are matched. User id: " + user.getId());

        return user;
    }

    public String getUserName() {
        User user = matchUser();

        return user.getName() + " " + user.getSurname();
    }

}
