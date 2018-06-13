package cz.muni.fi.pv243.ars.rest;

import cz.muni.fi.pv243.ars.persistence.model.Reservation;
import cz.muni.fi.pv243.ars.repository.ReservationRepository;

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
@Path("/reservations")
@RequestScoped
public class ReservationRESTService {

    @Inject
    private ReservationRepository reservationRepository;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Reservation> listAll()
    {
        return reservationRepository.findAll();
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReservation(@PathParam("id") Long id) {
        Reservation byId = reservationRepository.findById(id);
        if (byId == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(byId).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Reservation reservation) {
        if (reservation == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        reservationRepository.create(reservation);
        URI url = UriBuilder.fromResource(getClass()).path(reservation.getId().toString()).build();
        return Response.created(url).entity(reservation).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Reservation reservation) {
        if (reservation == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        if (reservationRepository.findById(reservation.getId()) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        reservationRepository.update(reservation);

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("id") Long id) {
        Reservation byId = reservationRepository.findById(id);
        if (byId == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        reservationRepository.delete(byId);
        return Response.ok().build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(Reservation reservation) {
        if (reservationRepository.findById(reservation.getId()) == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        reservationRepository.delete(reservation);
        return Response.ok().build();
    }


}
