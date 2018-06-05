package cz.muni.fi.pv243.ars.persistance.model;

import java.io.Serializable;
import java.sql.Date;
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
    private LocalDate from;

    @NotNull
    private LocalDate to;

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

    public LocalDate getFrom() {
        return from;
    }

    public Reservation setFrom(LocalDate from) {
        this.from = from;
        return this;
    }

    public LocalDate getTo() {
        return to;
    }

    public Reservation setTo(LocalDate to) {
        this.to = to;
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
        if (!getFrom().equals(that.getFrom()))
            return false;
        if (!getTo().equals(that.getTo()))
            return false;
        return getNumberOfPeople() != null ?
            getNumberOfPeople().equals(that.getNumberOfPeople()) :
            that.getNumberOfPeople() == null;

    }

    @Override
    public int hashCode() {
        int result = getOffer().hashCode();
        result = 31 * result + getFrom().hashCode();
        result = 31 * result + getTo().hashCode();
        result = 31 * result + (getNumberOfPeople() != null ? getNumberOfPeople().hashCode() : 0);
        return result;
    }
}
