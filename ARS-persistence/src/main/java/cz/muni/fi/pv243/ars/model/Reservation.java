package cz.muni.fi.pv243.ars.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Objects;

/**
 * Created by jsmolar on 5/19/18.
 */
@Entity
public class Reservation {

    @Id
    @GeneratedValue
    @Column(name = "reservation_id")
    private Long id;

    @ManyToOne
    @NotNull
    private Role host;

    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull
    private Offer offer;

    @NotNull
    private Date from;

    @NotNull
    private Date to;

    @NotNull
    private Integer numberOfPeople;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getHost() {
        return host;
    }

    public void setHost(Role host) {
        this.host = host;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
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
        if (this == o) return true;
        if (o == null || !(o instanceof Reservation)) return false;
        Reservation that = (Reservation) o;
        return getId() == that.getId() &&
                getHost().equals(that.getHost()) &&
                Objects.equals(getOffer(), that.getOffer()) &&
                getFrom().equals(that.getFrom()) &&
                getTo().equals(that.getTo()) &&
                getNumberOfPeople() == that.getNumberOfPeople();
    }

    @Override
    public int hashCode() {
        int result = 37 * getId().hashCode();
        result = result + 37 * getHost().hashCode()
                    + 37 * getOffer().hashCode()
                    + 37 * getFrom().hashCode()
                    + 37 * getTo().hashCode()
                    + 37 * getNumberOfPeople().hashCode();
        return result;
    }
}
