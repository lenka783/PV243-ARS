package cz.muni.fi.pv243.ars.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.Locale;

/**
 * Created by lenka smitalova on 6/19/18.
 */
@Named
@RequestScoped
public class CalendarBean {

    private Locale locale;
    private boolean popup = true;
    private String pattern = "yyyy-MM-dd";

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public boolean getPopup() {
        return popup;
    }

    public void setPopup(Boolean popup) {
        this.popup = popup;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
