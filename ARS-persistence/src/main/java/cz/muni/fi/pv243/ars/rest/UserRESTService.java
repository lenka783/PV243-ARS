package cz.muni.fi.pv243.ars.rest;

import cz.muni.fi.pv243.ars.persistence.model.User;
import cz.muni.fi.pv243.ars.repository.UserRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

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
    public List<User> listAll(){
        return userRepository.findAll();
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") Long id) {
        User byId = userRepository.findById(id);
        if (byId == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(byId).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(User user) {
        if (user == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        userRepository.create(user);
        URI url = UriBuilder.fromResource(getClass()).path(user.getId().toString()).build();
        return Response.created(url).entity(user).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(User user) {
        if (user == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        if (userRepository.findById(user.getId()) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        userRepository.update(user);

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("id") Long id) {
        User byId = userRepository.findById(id);
        if (byId == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        userRepository.delete(byId);
        return Response.ok().build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(User user) {
        if (userRepository.findById(user.getId()) == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        userRepository.delete(user);
        return Response.ok().build();
    }

}
