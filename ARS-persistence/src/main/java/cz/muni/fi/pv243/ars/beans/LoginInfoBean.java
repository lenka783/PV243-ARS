package cz.muni.fi.pv243.ars.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Created by lenka smitalova on 6/18/18.
 */
@Named
@RequestScoped
public class LoginInfoBean {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
