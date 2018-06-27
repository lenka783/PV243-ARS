package cz.muni.fi.pv243.ars.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import cz.muni.fi.pv243.ars.controller.UserController;
import cz.muni.fi.pv243.ars.persistence.enumeration.AccommodationType;
import cz.muni.fi.pv243.ars.persistence.model.Address;
import cz.muni.fi.pv243.ars.persistence.model.Offer;
import cz.muni.fi.pv243.ars.repository.OfferRepository;
import cz.muni.fi.pv243.ars.repository.UserRepository;

@Named
@RequestScoped
public class CreateOfferBean implements Serializable {

    @Inject
    private OfferRepository offerRepository;

    @Inject
    private UserController userController;

    private AccommodationType accommodationType;
    private AddressBean address = new AddressBean();
    private String name;
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

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void create() throws IOException {
        Offer offer = new Offer();
                offer.setAccommodationType(accommodationType);
                offer.setAddress(getAddressFromBean());
                offer.setAnimalFriendly(animalFriendly);
                offer.setName(name);
                offer.setCapacity(capacity);
                offer.setPrice(price);
                offer.setSmokerFriendly(smokerFriendly);
                offer.setUser(userController.matchUser());
        
        try {
            offerRepository.create(offer);
            FacesMessage msg = new FacesMessage("Offer created!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e ) {
            FacesMessage msg = new FacesMessage("Something went wrong, please try again later.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("offers.jsf?for_user=true");
    }

    private Address getAddressFromBean() {
        Address result = new Address();
                result.setPostCode(address.getPostCode());
                result.setCountry(address.getCountry());
                result.setState(address.getState());
                result.setCity(address.getCity());
                result.setStreet(address.getStreet());
                return result;
    }
}
