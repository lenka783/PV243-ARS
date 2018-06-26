package cz.muni.fi.pv243.ars.beans;

import cz.muni.fi.pv243.ars.persistence.enumeration.AccommodationType;
import cz.muni.fi.pv243.ars.persistence.model.Address;
import cz.muni.fi.pv243.ars.persistence.model.Offer;
import cz.muni.fi.pv243.ars.repository.OfferRepository;
import cz.muni.fi.pv243.ars.repository.UserRepository;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class CreateOfferBean implements Serializable {

    @Inject
    private OfferRepository offerRepository;

    @Inject
    private UserRepository userRepository;

    private AccommodationType accommodationType;
    private Address address = new Address();
    private Integer price;
    private Integer capacity;
    private boolean animalFriendly;
    private boolean smokerFriendly;

    public AccommodationType getAccommodationType() {
        return accommodationType;
    }

    public void setAccommodationType(AccommodationType accommodationType) {
        this.accommodationType = accommodationType;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public boolean isAnimalFriendly() {
        return animalFriendly;
    }

    public void setAnimalFriendly(boolean animalFriendly) {
        this.animalFriendly = animalFriendly;
    }

    public boolean isSmokerFriendly() {
        return smokerFriendly;
    }

    public void setSmokerFriendly(boolean smokerFriendly) {
        this.smokerFriendly = smokerFriendly;
    }

    public String create() {
        Offer offer = new Offer();
        offer.setAccommodationType(accommodationType);
        offer.setAddress(address);
        offer.setAnimalFriendly(animalFriendly);
        offer.setCapacity(capacity);
        offer.setPrice(price);
        offer.setSmokerFriendly(smokerFriendly);
        offer.setUser(userRepository.findById(0l));

        try {
            offerRepository.create(offer);
        } catch (Exception e ) {
            FacesMessage msg = new FacesMessage("Something went wrong, please try again later.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "offers";
        }
        return "offers";
    }
}
