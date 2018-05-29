package cz.muni.fi.pv243.ars.model;

import cz.muni.fi.pv243.ars.enumeration.AccomodationType;
import cz.muni.fi.pv243.ars.validation.AddressConstraint;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by jsmolar on 5/19/18.
 */
@Entity
public class Offer {

    @Id
    @GeneratedValue
    @Column(name = "offer_id")
    private Long id;

    @NotNull
    @ManyToOne
    private Role tenant;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getTenant() {
        return tenant;
    }

    public void setTenant(Role tenant) {
        this.tenant = tenant;
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
        if (this == o) return true;
        if (o == null || !(o instanceof Offer)) return false;

        final Offer offer = (Offer) o;

        if (id != null ? !id.equals(offer.id) : offer.id != null) return false;
        if (tenant != null ? !tenant.equals(offer.tenant) : offer.tenant != null) return false;
        if (address != null ? !address.equals(offer.address) : offer.address != null) return false;
        if (capacity != null ? !capacity.equals(offer.capacity) : offer.capacity != null) return false;
        if (accomodationType != offer.accomodationType) return false;
        if (isAnimalFriendly != null ? !isAnimalFriendly.equals(offer.isAnimalFriendly) : offer.isAnimalFriendly != null)
            return false;
        return isSmokerFriendly != null ? isSmokerFriendly.equals(offer.isSmokerFriendly) : offer.isSmokerFriendly == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (tenant != null ? tenant.hashCode() : 0);
        result = 7 * result + (address != null ? address.hashCode() : 0);
        result = 23 * result + (capacity != null ? capacity.hashCode() : 0);
        result = 31 * result + (accomodationType != null ? accomodationType.hashCode() : 0);
        result = 31 * result + (isAnimalFriendly != null ? isAnimalFriendly.hashCode() : 0);
        result = 31 * result + (isSmokerFriendly != null ? isSmokerFriendly.hashCode() : 0);
        return result;
    }
}
