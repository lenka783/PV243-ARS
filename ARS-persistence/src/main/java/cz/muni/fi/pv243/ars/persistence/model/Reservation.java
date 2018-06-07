package cz.muni.fi.pv243.ars.persistence.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by jsmolar on 5/19/18.
 */
@Entity
@XmlRootElement
public class Reservation implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "reservation_id")
    private Long id;

    @ManyToOne
    @NotNull
    private Offer offer;

    @ManyToOne
    private User user;

    @NotNull
    private LocalDate fromDate;

    @NotNull
    private LocalDate toDate;

    @NotNull
    private Integer numberOfPeople;

    public Reservation() {
    }

    public Reservation(Offer offer, User user, LocalDate from, LocalDate to, Integer numberOfPeople) {
        this.offer = offer;
        this.user = user;
        this.fromDate = from;
        this.toDate = to;
        this.numberOfPeople = numberOfPeople;
    }

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

        if (!getOffer().equals(that.getOffer()))
            return false;
        if (!getFromDate().equals(that.getFromDate()))
            return false;
        if (!getToDate().equals(that.getToDate()))
            return false;
        return getNumberOfPeople() != null ?
            getNumberOfPeople().equals(that.getNumberOfPeople()) :
            that.getNumberOfPeople() == null;

    }

    @Override
    public int hashCode() {
        int result = getOffer().hashCode();
        result = 31 * result + getFromDate().hashCode();
        result = 31 * result + getToDate().hashCode();
        result = 31 * result + (getNumberOfPeople() != null ? getNumberOfPeople().hashCode() : 0);
        return result;
    }
}