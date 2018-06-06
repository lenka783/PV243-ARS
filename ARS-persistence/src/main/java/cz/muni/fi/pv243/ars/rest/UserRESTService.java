package cz.muni.fi.pv243.ars.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import cz.muni.fi.pv243.ars.persistence.enumeration.UserRole;
import cz.muni.fi.pv243.ars.persistence.model.User;
import cz.muni.fi.pv243.ars.repository.UserRepository;

/**
 * Created by jsmolar on 6/5/18.
 */
@Path("/users")
@RequestScoped
public class UserRESTService {

    @Inject
    private UserRepository userRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> listAllTenants() {
        return userRepository.findAllForRole(UserRole.TENANT);
    }

}
