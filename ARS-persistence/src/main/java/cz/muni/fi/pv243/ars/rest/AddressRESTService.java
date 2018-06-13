package cz.muni.fi.pv243.ars.rest;

import cz.muni.fi.pv243.ars.persistence.model.Address;
import cz.muni.fi.pv243.ars.repository.AddressRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

/**
 * Created by mminatova on 9/6/18.
 */
@Path("/addresses")
@RequestScoped
public class AddressRESTService {

    @Inject
    private AddressRepository addressRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Address> listAll(){
        return addressRepository.findAll();
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAddress(@PathParam("id") Long id) {
        Address byId = addressRepository.findById(id);
        if (byId == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(byId).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Address address) {
        if (address == null) {
            return Response.status(Status.BAD_REQUEST).build();
        }

        addressRepository.create(address);
        URI url = UriBuilder.fromResource(getClass()).path(address.getId().toString()).build();
        return Response.created(url).entity(address).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Address address) {
        if (address == null) {
            return Response.status(Status.BAD_REQUEST).build();
        }

        if (addressRepository.findById(address.getId()) == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        addressRepository.update(address);

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("id") Long id) {
        Address byId = addressRepository.findById(id);
        if (byId == null){
            return Response.status(Status.NOT_FOUND).build();
        }
        addressRepository.delete(byId);
        return Response.ok().build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(Address address) {
        if (addressRepository.findById(address.getId()) == null)
            return Response.status(Status.NOT_FOUND).build();
        addressRepository.delete(address);
        return Response.ok().build();
    }

}
