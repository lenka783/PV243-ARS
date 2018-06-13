package cz.muni.fi.pv243.ars.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;

import cz.muni.fi.pv243.ars.persistence.model.Offer;
import cz.muni.fi.pv243.ars.persistence.model.User;
import cz.muni.fi.pv243.ars.repository.OfferRepository;
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
    @Produces("application/json")
    public List<User> listAll(){
        return userRepository.findAll();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public void create(User user) {
        userRepository.create(user);
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public User update(User user) {
        return userRepository.update(user);
    }

    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    public void deleteById(@PathParam("id") Long id) {
        User byId = userRepository.findById(id);
        if (byId != null)
            userRepository.delete(byId);
    }

    @DELETE
    @Consumes("application/json")
    public void delete(User user) {
        userRepository.delete(user);
    }

}
