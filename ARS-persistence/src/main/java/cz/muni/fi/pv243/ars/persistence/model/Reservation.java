package cz.muni.fi.pv243.ars.persistence.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import cz.muni.fi.pv243.ars.persistence.validation.ReservationDateRangeConstraint;

import java.io.Serializable;
import java.time.LocalDate;

import javax.faces.annotation.ManagedProperty;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by jsmolar on 5/19/18.
 */
@Entity
@ReservationDateRangeConstraint
//@XmlRootElement
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Reservation implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(updatable = false)
    @NotNull
    private Offer offer;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(updatable = false)
    @NotNull
    private User user;

    @NotNull
    private LocalDate fromDate;

    @NotNull
    private LocalDate toDate;

    @NotNull
    private Integer numberOfPeople;

    public Long getId() {
        return id;
    }

    public Reservation setId(Long id) {
        this.id = id;
        return this;
    }

    public Offer getOffer() {
        return offer;
    }

    public Reservation setOffer(Offer offer) {
        this.offer = offer;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Reservation setUser(User host) {
        this.user = host;
        return this;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public Reservation setFromDate(LocalDate from) {
        this.fromDate = from;
        return this;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public Reservation setToDate(LocalDate to) {
        this.toDate = to;
        return this;
    }

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public Reservation setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Reservation that = (Reservation) o;

        if (getUser() == null ? that.getUser() != null : !getUser().equals(that.getUser())) {
            return false;
        }
        if (getOffer() == null ? that.getOffer() != null : !getOffer().equals(that.getOffer()))
            return false;
        if (getFromDate() == null ? that.getFromDate() != null : !getFromDate().equals(that.getFromDate()))
            return false;
        if (getToDate() == null ? that.getToDate() != null : !getToDate().equals(that.getToDate()))
            return false;
        return getNumberOfPeople() != null ?
            getNumberOfPeople().equals(that.getNumberOfPeople()) :
            that.getNumberOfPeople() == null;

    }

    @Override
    public int hashCode() {
        int result = 31 * (getOffer() == null ? 0 : getOffer().hashCode());
        result = 31 * result + (getUser() == null ? 0 : getUser().hashCode());
        result = 31 * result + (getFromDate() == null ? 0 : getFromDate().hashCode());
        result = 31 * result + (getToDate() == null ? 0 : getToDate().hashCode());
        result = 31 * result + (getNumberOfPeople() == null ? 0 : getNumberOfPeople().hashCode());
        return result;
    }
}
