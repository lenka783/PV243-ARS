package cz.muni.fi.pv243.ars.persistence.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import cz.muni.fi.pv243.ars.persistence.enumeration.AccommodationType;
import cz.muni.fi.pv243.ars.persistence.validation.AddressConstraint;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by jsmolar on 5/19/18.
 */
@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Offer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 1, max = 25)
    @Column(nullable = false)
    private String name;

    @NotNull
    @AddressConstraint
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(updatable = false)
    private Address address;

    @NotNull
    @Min(0)
    @Max(20)
    private Integer capacity;

    @NotNull
    @Min(0)
    @Max(500000)
    private Integer price;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private AccommodationType accommodationType;

    private Boolean isAnimalFriendly;

    private Boolean isSmokerFriendly;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(updatable = false)
    @NotNull
    private User user;

    @OneToMany(mappedBy = "offer")
    @PrimaryKeyJoinColumn
    private Set<Reservation> reservations = new HashSet<>();

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "offer", fetch = FetchType.EAGER)
    private Set<UserComment> userComments = new HashSet<>();

    public Long getId() {
        return id;
    }

    public Offer setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Offer setName(String name) {
        this.name = name;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public Offer setAddress(Address address) {
        this.address = address;
        return this;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Offer setCapacity(Integer capacity) {
        this.capacity = capacity;
        return this;
    }

    public AccommodationType getAccommodationType() {
        return accommodationType;
    }

    public Offer setAccommodationType(AccommodationType accommodationType) {
        this.accommodationType = accommodationType;
        return this;
    }

    public Boolean getAnimalFriendly() {
        return isAnimalFriendly;
    }

    public Offer setAnimalFriendly(Boolean animalFriendly) {
        isAnimalFriendly = animalFriendly;
        return this;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Boolean getSmokerFriendly() {
        return isSmokerFriendly;
    }

    public Offer setSmokerFriendly(Boolean smokerFriendly) {
        isSmokerFriendly = smokerFriendly;
        return this;
    }

    public Offer addReservation(Reservation reservation) {
        reservations.add(reservation);
        return this;
    }

    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    public Integer getPrice() {
        return price;
    }

    public Offer setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Offer addUserComment(UserComment userComment) {
        userComments.add(userComment);
        return this;
    }

    public void removeUserComment(UserComment userComment) {
        userComments.remove(userComment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Offer offer = (Offer) o;

        if (getName() != null ? !getName().equals(offer.getName()) : offer.getName() != null)
            return false;
        if (getName() != null ? !getAddress().equals(offer.getAddress()) : offer.getAddress() != null)
            return false;
        return getAccommodationType() == offer.getAccommodationType();

    }

    @Override
    public int hashCode() {
        int result = getUser() == null ? 0 : getUser().hashCode();
        result = 31 * result + (getAddress() == null ? 0 : getAddress().hashCode());
        result = 31 * result + (getAccommodationType() == null ? 0 : getAccommodationType().hashCode());
        return result;
    }
}
