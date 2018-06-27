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
        System.out.println("Create init");
        Offer offer = new Offer();
        System.out.println("Offer init");
        offer.setAccommodationType(accommodationType);
        System.out.println("Offer accommodation");
        offer.setAddress(getAddressFromBean());
        System.out.println("Offer address");
        offer.setAnimalFriendly(animalFriendly);
        System.out.println("Offer animal");
        offer.setCapacity(capacity);
        System.out.println("Offer capacity");
        offer.setPrice(price);
        System.out.println("Offer price");
        offer.setSmokerFriendly(smokerFriendly);
        System.out.println("Offer smoker");
        offer.setUser(userController.matchUser());
        System.out.println("Offer user");

        try {
            offerRepository.create(offer);
            FacesMessage msg = new FacesMessage("Offer created!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e ) {
            FacesMessage msg = new FacesMessage("Something went wrong, please try again later.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("offers.jsf");
    }

    private Address getAddressFromBean() {
        Address result = new Address();
        System.out.println("Address init");
        result.setPostCode(address.getPostCode());
        System.out.println("Address ZIP");
        result.setCountry(address.getCountry());
        System.out.println("Address country");
        result.setState(address.getState());
        System.out.println("Address state");
        result.setCity(address.getCity());
        System.out.println("Address city");
        result.setStreet(address.getStreet());
        System.out.println("Address street");
        return result;
    }
}
