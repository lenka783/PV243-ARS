package cz.muni.fi.pv243.ars.rest;

import cz.muni.fi.pv243.ars.persistence.model.Offer;
import cz.muni.fi.pv243.ars.repository.OfferRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
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
    @Produces("application/json")
    public List<Offer> listAll(){
        return offerRepository.findAll();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public void create(Offer offer) {
        offerRepository.create(offer);
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Offer update(Offer offer) {
        return offerRepository.update(offer);
    }

    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    public void deleteById(@PathParam("id") Long id) {
        Offer byId = offerRepository.findById(id);
        if (byId != null)
            offerRepository.delete(byId);
    }

    @DELETE
    @Consumes("application/json")
    public void delete(Offer offer) {
        offerRepository.delete(offer);
    }

}
