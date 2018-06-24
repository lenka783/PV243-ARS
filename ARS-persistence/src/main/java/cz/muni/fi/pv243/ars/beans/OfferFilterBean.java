package cz.muni.fi.pv243.ars.beans;

import cz.muni.fi.pv243.ars.persistence.enumeration.AccommodationType;
import cz.muni.fi.pv243.ars.persistence.model.Offer;
import org.richfaces.model.Filter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@RequestScoped
public class OfferFilterBean {

    private String locationFilter;
    private Date checkInFilter;
    private Date checkOutFilter;
    private Long capacityFilter;
    private Long priceFilter;
    private boolean animalFriendlyFilter;
    private boolean smokerFriendlyFilter;
    private AccommodationType accommodationTypeFilter;
    private List<SelectItem> accomodationOptions = null;

    @PostConstruct
    public void init() {
        accomodationOptions = new ArrayList<SelectItem>();
        for (AccommodationType type : AccommodationType.values()) {
            accomodationOptions.add(new SelectItem(type.name()));
        }
    }

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
                if (capacity == null || capacity == 0 || capacity.compareTo(item.getPrice().longValue()) >= 0) {
                    return true;
                }
                return false;
            }
        };
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

    public List<SelectItem> getAccomodationOptions() {
        return accomodationOptions;
    }

    public void setAccomodationOptions(List<SelectItem> accomodationOptions) {
        this.accomodationOptions = accomodationOptions;
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
