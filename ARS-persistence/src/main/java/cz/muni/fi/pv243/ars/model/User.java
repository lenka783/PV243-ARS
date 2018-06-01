package cz.muni.fi.pv243.ars.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import cz.muni.fi.pv243.ars.enumeration.UserRole;
import cz.muni.fi.pv243.ars.validation.AddressConstraint;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by jsmolar on 5/10/18.
 */
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @NotNull
    @Size(min = 1, max = 25)
    private String name;

    @NotNull
    @Size(min = 1, max = 25)
    private String surname;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotNull
    private String password;

    @AddressConstraint
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Address address;

    private LocalDate dateOfBirth;

    @NotNull
    private Boolean isActive = true;

    @ElementCollection
    @CollectionTable(name="user_roles")
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name="roles")
    private Set<UserRole> roles = new HashSet();

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "user")
    private Set<Offer> offers = new HashSet();

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "user")
    private Set<Reservation> reservations = new HashSet();

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public User setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public User setAddress(Address address) {
        this.address = address;
        return this;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public User setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public Boolean getActive() {
        return isActive;
    }

    public User setActive(Boolean active) {
        isActive = active;
        return this;
    }

    public Set<UserRole> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

    public void addRole(UserRole role) {
        roles.add(role);
    }

    public void removeRole(UserRole role) {
        roles.remove(roles);
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

        User user = (User) o;

        if (!getName().equals(user.getName()))
            return false;
        if (!getSurname().equals(user.getSurname()))
            return false;
        return getEmail().equals(user.getEmail());

    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getSurname().hashCode();
        result = 31 * result + getEmail().hashCode();
        return result;
    }
}
