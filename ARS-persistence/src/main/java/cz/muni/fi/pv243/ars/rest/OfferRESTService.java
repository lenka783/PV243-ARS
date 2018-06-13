package cz.muni.fi.pv243.ars.rest;

import cz.muni.fi.pv243.ars.persistence.model.Offer;
import cz.muni.fi.pv243.ars.repository.OfferRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

/**
 * Created by mminatova on 9/6/18.
 */
@Path("/offers")
@RequestScoped
public class OfferRESTService {

    @Inject
    private OfferRepository offerRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Offer> listAll(){
        return offerRepository.findAll();
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOffer(@PathParam("id") Long id) {
        Offer byId = offerRepository.findById(id);
        if (byId == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(byId).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Offer offer) {
        if (offer == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        offerRepository.create(offer);
        URI url = UriBuilder.fromResource(getClass()).path(offer.getId().toString()).build();
        return Response.created(url).entity(offer).build();
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Offer offer) {
        if (offer == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        if (offerRepository.findById(offer.getId()) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        offerRepository.update(offer);

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("id") Long id) {
        Offer byId = offerRepository.findById(id);
        if (byId == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        offerRepository.delete(byId);
        return Response.ok().build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(Offer offer) {
        if (offerRepository.findById(offer.getId()) == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        offerRepository.delete(offer);
        return Response.ok().build();
    }

}
