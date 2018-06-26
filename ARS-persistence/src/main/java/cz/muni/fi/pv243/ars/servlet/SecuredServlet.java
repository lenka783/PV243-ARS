package cz.muni.fi.pv243.ars.servlet;

import javax.annotation.security.DeclareRoles;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Created by jsmolar on 6/18/18.
 */
@ServletSecurity(@HttpConstraint(rolesAllowed = { "admin","user" }))
@DeclareRoles("user")
@WebServlet(name = "SecuredServlet", urlPatterns = { "/services/" }, loadOnStartup = 1)
public class SecuredServlet extends HttpServlet {
}
