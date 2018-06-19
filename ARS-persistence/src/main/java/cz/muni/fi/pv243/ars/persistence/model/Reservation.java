package cz.muni.fi.pv243.ars.persistence.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import cz.muni.fi.pv243.ars.persistence.validation.ReservationDateRangeConstraint;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by jsmolar on 5/19/18.
 */
@Entity
@ReservationDateRangeConstraint
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Offer offer;

    @ManyToOne
    private User user;

    @NotNull
    private LocalDate fromDate;

    @NotNull
    private LocalDate toDate;

    @NotNull
    private Integer numberOfPeople;

    @NotNull
    private int assignedId;

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

    public int getAssignedId() {
        return assignedId;
    }

    public Reservation setAssignedId(int assignedId) {
        this.assignedId = assignedId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Reservation that = (Reservation) o;

        if (!getOffer().equals(that.getOffer()))
            return false;
        if (!getFromDate().equals(that.getFromDate()))
            return false;
        if (!getToDate().equals(that.getToDate()))
            return false;
        if (getAssignedId() !=(that.getAssignedId()))
            return false;
        return getNumberOfPeople() != null ?
            getNumberOfPeople().equals(that.getNumberOfPeople()) :
            that.getNumberOfPeople() == null;

    }

    @Override
    public int hashCode() {
        int result = 31 * getAssignedId();
        result = 31 * result + getFromDate().hashCode();
        result = 31 * result + getToDate().hashCode();
        result = 31 * result + getNumberOfPeople().hashCode();
        return result;
    }
}
