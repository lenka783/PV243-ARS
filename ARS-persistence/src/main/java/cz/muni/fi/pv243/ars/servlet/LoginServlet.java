package cz.muni.fi.pv243.ars.servlet;

import cz.muni.fi.pv243.ars.persistence.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        StringBuilder errorMessage = new StringBuilder();
    }
}
