package cz.muni.fi.pv243.ars.rest;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by jsmolar on 6/14/18.
 */
@Path("/")
@RequestScoped
public class LoginModule {

    @GET
    @Path("/login")
    public void logIn() {

    }

    @GET
    @Path("/logout")
    public void logOut() {

    }

}
