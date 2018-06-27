package cz.muni.fi.pv243.ars.beans;

import cz.muni.fi.pv243.ars.persistence.enumeration.AccommodationType;
import cz.muni.fi.pv243.ars.persistence.model.Address;
import cz.muni.fi.pv243.ars.persistence.model.Offer;
import org.richfaces.model.Filter;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named
@ViewScoped
public class OfferFilterBean implements Serializable {

    private String locationFilter;
    private Date checkInFilter;
    private Date checkOutFilter;
    private Long capacityFilter;
    private Long priceFilter;
    private boolean animalFriendlyFilter;
    private boolean smokerFriendlyFilter;
    private AccommodationType accommodationTypeFilter;

    public Filter<?> getPriceFilterImpl() {
        return new Filter<Offer>() {
            public boolean accept(Offer item) {
                Long price = getPriceFilter();
                if (price == null || price == 0 || price.compareTo(item.getPrice().longValue()) >= 0) {
                    return true;
                }
                return false;
            }
        };
    }

    public Filter<?> getCapacityFilterImpl() {
        return new Filter<Offer>() {
            public boolean accept(Offer item) {
                Long capacity = getCapacityFilter();
                if (capacity == null || capacity == 0 || capacity.compareTo(item.getCapacity().longValue()) <= 0) {
                    return true;
                }
                return false;
            }
        };
    }

    public Filter<?> getLocationFilterImpl() {
        return new Filter<Offer>() {
            public boolean accept(Offer item) {

                AddressBean address = parseLocation();
                Address itemAddress = item.getAddress();
                if (address == null ||
                        (address.getCity().equals(itemAddress.getCity())
                                && address.getCountry().equals(itemAddress.getCountry()))) {
                    return true;
                }
                return false;
            }
        };
    }

    public Filter<?> getAnimalFriendlyFilterImpl() {
        return new Filter<Offer>() {
            public boolean accept(Offer item) {
                if (!animalFriendlyFilter) {
                    return true;
                }
                return item.getAnimalFriendly();
            }
        };
    }

    public Filter<?> getSmokerFriendlyFilterImpl() {
        return new Filter<Offer>() {
            public boolean accept(Offer item) {
                if (!smokerFriendlyFilter) {
                    return true;
                }
                return item.getSmokerFriendly();
            }
        };
    }

    private AddressBean parseLocation() {
        String location = getLocationFilter();
        if (location == null) {
            return null;
        }
        String[] splitLocation = location.split(" - [(]");
        if (splitLocation.length != 2) {
            return null;
        }
        splitLocation[1].replace(")", "");
        AddressBean addressBean = new AddressBean();
        addressBean.setCity(splitLocation[0]);
        addressBean.setCountry(splitLocation[1]);
        return addressBean;
    }

    public String getLocationFilter() {
        return locationFilter;
    }

    public void setLocationFilter(String locationFilter) {
        this.locationFilter = locationFilter;
    }

    public Date getCheckInFilter() {
        return checkInFilter;
    }

    public void setCheckInFilter(Date checkInFilter) {
        this.checkInFilter = checkInFilter;
    }

    public Date getCheckOutFilter() {
        return checkOutFilter;
    }

    public void setCheckOutFilter(Date checkOutFilter) {
        this.checkOutFilter = checkOutFilter;
    }

    public Long getCapacityFilter() {
        return capacityFilter;
    }

    public void setCapacityFilter(Long capacityFilter) {
        this.capacityFilter = capacityFilter;
    }

    public Long getPriceFilter() {
        return priceFilter;
    }

    public void setPriceFilter(Long priceFilter) {
        this.priceFilter = priceFilter;
    }

    public AccommodationType getAccommodationTypeFilter() {
        return accommodationTypeFilter;
    }

    public void setAccommodationTypeFilter(AccommodationType accommodationTypeFilter) {
        this.accommodationTypeFilter = accommodationTypeFilter;
    }

    public boolean isAnimalFriendlyFilter() {
        return animalFriendlyFilter;
    }

    public void setAnimalFriendlyFilter(boolean animalFriendlyFilter) {
        this.animalFriendlyFilter = animalFriendlyFilter;
    }

    public boolean isSmokerFriendlyFilter() {
        return smokerFriendlyFilter;
    }

    public void setSmokerFriendlyFilter(boolean smokerFriendlyFilter) {
        this.smokerFriendlyFilter = smokerFriendlyFilter;
    }
}
