package cz.muni.fi.pv243.ars.rest;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Created by jsmolar on 6/24/18.
 */
@Path("/login")
@RequestScoped
public class LoginService {

    @GET
    @Path("{redirectPage}")
    public void login(@PathParam("redirectPage") String redirectPage) throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(redirectPage + ".jsf?faces-redirect=true");
    }

}
