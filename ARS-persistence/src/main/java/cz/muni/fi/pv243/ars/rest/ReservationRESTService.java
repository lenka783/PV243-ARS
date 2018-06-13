package cz.muni.fi.pv243.ars.rest;

import cz.muni.fi.pv243.ars.persistence.model.Reservation;
import cz.muni.fi.pv243.ars.persistence.model.User;
import cz.muni.fi.pv243.ars.repository.ReservationRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;
/**
 * Created by mminatova on 9/6/18.
 */
@Path("/reservations")
@RequestScoped
public class ReservationRESTService {

    @Inject
    private ReservationRepository reservationRepository;

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces("application/json")
    public Reservation findById(@PathParam("id") Long id) {
        return reservationRepository.findById(id);
    }

    @GET
    @Consumes("application/json")
    @Produces("application/json")
    public List<Reservation> listAllForUser(User user){
        return reservationRepository.findAllForUser(user);
    }

    @POST
    @Consumes("application/json")
    public void create(Reservation reservation) {
        reservationRepository.create(reservation);
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Reservation update(Reservation reservation) {
        return reservationRepository.update(reservation);
    }

    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    public void deleteById(@PathParam("id") Long id) {
        Reservation byId = reservationRepository.findById(id);
        if (byId != null)
            reservationRepository.delete(byId);
    }

    @DELETE
    @Consumes("application/json")
    public void delete(Reservation reservation) {
        reservationRepository.delete(reservation);
    }


}
