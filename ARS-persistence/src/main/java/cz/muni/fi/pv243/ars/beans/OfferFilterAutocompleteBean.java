package cz.muni.fi.pv243.ars.beans;

import cz.muni.fi.pv243.ars.persistence.enumeration.AccommodationType;
import cz.muni.fi.pv243.ars.persistence.model.Address;
import cz.muni.fi.pv243.ars.repository.AddressRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "offerFilterAutocomplete")
@RequestScoped
public class OfferFilterAutocompleteBean {

    @Inject
    private AddressRepository addressRepository;

    private List<Address> addresses = new ArrayList<>();
    private List<String> accomodationOptions;
    private String value;

    @PostConstruct
    public void init() {
        accomodationOptions = new ArrayList<>();
        for (AccommodationType type : AccommodationType.values()) {
            accomodationOptions.add(type.name());
        }
        addresses = new ArrayList<>();
        for (Address address : addressRepository.findAll()) {
            System.out.println(address.getCity());
            addresses.add(address);
        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<String> getAccomodationOptions() {
        return accomodationOptions;
    }

    public void setAccomodationOptions(List<String> accomodationOptions) {
        this.accomodationOptions = accomodationOptions;
    }
}

