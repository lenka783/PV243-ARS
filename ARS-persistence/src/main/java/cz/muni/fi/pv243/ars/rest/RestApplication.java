package cz.muni.fi.pv243.ars.rest;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
/**
 * Created by mminatova on 9/6/18.
 */
@ApplicationPath("/")
//TODO: @DeclareRoles({ "admin", "user" })
public class RestApplication extends Application {
}
