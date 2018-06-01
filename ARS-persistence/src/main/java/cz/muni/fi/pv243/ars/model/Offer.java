package cz.muni.fi.pv243.ars.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import cz.muni.fi.pv243.ars.enumeration.AccomodationType;
import cz.muni.fi.pv243.ars.validation.AddressConstraint;

/**
 * Created by jsmolar on 5/19/18.
 */
@Entity
@XmlRootElement
public class Offer implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "offer_id")
    private Long id;

    @NotNull
    @AddressConstraint
    @OneToOne(mappedBy = "offer", cascade = CascadeType.ALL)
    private Address address;

    @NotNull
    private Integer capacity;

    @NotNull
    @Enumerated
    private AccomodationType accomodationType;

    @NotNull
    private Boolean isAnimalFriendly;

    @NotNull
    private Boolean isSmokerFriendly;

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public AccomodationType getAccomodationType() {
        return accomodationType;
    }

    public void setAccomodationType(AccomodationType accomodationType) {
        this.accomodationType = accomodationType;
    }

    public Boolean getAnimalFriendly() {
        return isAnimalFriendly;
    }

    public void setAnimalFriendly(Boolean animalFriendly) {
        isAnimalFriendly = animalFriendly;
    }

    public Boolean getSmokerFriendly() {
        return isSmokerFriendly;
    }

    public void setSmokerFriendly(Boolean smokerFriendly) {
        isSmokerFriendly = smokerFriendly;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Offer offer = (Offer) o;

        if (!getAddress().equals(offer.getAddress()))
            return false;
        if (!getCapacity().equals(offer.getCapacity()))
            return false;
        if (getAccomodationType() != offer.getAccomodationType())
            return false;
        if (isAnimalFriendly != null ?
            !isAnimalFriendly.equals(offer.isAnimalFriendly) :
            offer.isAnimalFriendly != null)
            return false;
        return isSmokerFriendly != null ?
            isSmokerFriendly.equals(offer.isSmokerFriendly) :
            offer.isSmokerFriendly == null;

    }

    @Override
    public int hashCode() {
        int result = getAddress().hashCode();
        result = 31 * result + getCapacity().hashCode();
        result = 31 * result + getAccomodationType().hashCode();
        result = 31 * result + (isAnimalFriendly != null ? isAnimalFriendly.hashCode() : 0);
        result = 31 * result + (isSmokerFriendly != null ? isSmokerFriendly.hashCode() : 0);
        return result;
    }
}
