package cz.muni.fi.pv243.ars.persistence.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import cz.muni.fi.pv243.ars.persistence.enumeration.UserRole;
import cz.muni.fi.pv243.ars.persistence.validation.AddressConstraint;
import cz.muni.fi.pv243.ars.persistence.validation.RoleOwnership;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jsmolar on 5/10/18.
 */
@Entity
@RoleOwnership
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class User implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String keycloakPrincipal;

    @NotNull
    @Size(min = 1, max = 25)
    @Column(updatable = false, nullable = false)
    private String name;

    @NotNull
    @Size(min = 1, max = 25)
    @Column(nullable = false)
    private String surname;

    @NotNull
    @NotEmpty
    @Email
    @Column(updatable = false, nullable = false)
    private String email;

    @NotNull
    @Column(updatable = false, nullable = false)
    private String password;

    @AddressConstraint
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @NotNull
    private Address address;

    @NotNull
    @Column(updatable = false, nullable = false)
    private LocalDate dateOfBirth;

    @NotNull
    private Boolean isActive = true;

    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name="user_roles")
    @Enumerated(EnumType.STRING)
    @Column(name="roles")
    private Set<UserRole> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Offer> offers = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Set<Reservation> reservations = new HashSet<>();

    public User() {
    }

    public User(String name, String surname, String email, String password, Address address, LocalDate dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.isActive = true;
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getKeycloakPrincipal() {
        return keycloakPrincipal;
    }

    public User setKeycloakPrincipal(String keycloakPrincipal) {
        this.keycloakPrincipal = keycloakPrincipal;
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

    public User addRole(UserRole role) {
        roles.add(role);
        return this;
    }

    public void removeRole(UserRole role) {
        roles.remove(roles);
    }

    public Set<Offer> getOffers() {
        return Collections.unmodifiableSet(offers);
    }

    public User addOffer(Offer offer) {
        offers.add(offer);
        return this;
    }

    public void removeOffer(Offer offer) {
        offers.remove(offer);
    }

    public Set<Reservation> getReservations() {
        return Collections.unmodifiableSet(reservations);
    }

    public User addReservation(Reservation reservation) {
        reservations.add(reservation);
        return this;
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
