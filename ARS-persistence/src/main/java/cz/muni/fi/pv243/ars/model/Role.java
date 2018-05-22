package cz.muni.fi.pv243.ars.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Created by jsmolar on 5/19/18.
 */
@Entity
public class Role {

    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Long id;

    @Enumerated
    private Role role;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "role")
    private Set<Offer> offers = new HashSet<Offer>();

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "role")
    private Set<Reservation> reservations = new HashSet<Reservation>();

    public Long getId() {
        return id;
    }

    public Role setId(Long id) {
        this.id = id;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public Role setRole(Role role) {
        this.role = role;
        return this;
    }

    public Set<Offer> getOffers() {
        return Collections.unmodifiableSet(offers);
    }

    public void addOffer(Offer offer) {
        offers.add(offer);
    }

    public void removeOffer(Offer offer) {
        offers.remove(offer);
    }

    public Set<Reservation> getReservations() {
        return Collections.unmodifiableSet(reservations);
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Role role1 = (Role) o;

        if (!getId().equals(role1.getId()))
            return false;
        return getRole() != null ? getRole().equals(role1.getRole()) : role1.getRole() == null;

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getRole() != null ? getRole().hashCode() : 0);
        return result;
    }
}
