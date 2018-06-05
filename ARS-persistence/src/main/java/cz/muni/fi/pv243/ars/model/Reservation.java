package cz.muni.fi.pv243.ars.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by jsmolar on 5/19/18.
 */
@Entity
public class Reservation {

    @Id
    @GeneratedValue
    @Column(name = "reservation_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Offer offer;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @Temporal(TemporalType.DATE)
    private Date from;

    @Temporal(TemporalType.DATE)
    private Date to;

    @NotNull
    private Integer numberOfPeople;

    public Reservation() {
    }

    public Reservation(Offer offer, User user, Date from, Date to, Integer numberOfPeople) {
        this.offer = offer;
        this.user = user;
        this.from = from;
        this.to = to;
        this.numberOfPeople = numberOfPeople;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
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
