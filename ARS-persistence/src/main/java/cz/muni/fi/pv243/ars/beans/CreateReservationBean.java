package cz.muni.fi.pv243.ars.beans;

import cz.muni.fi.pv243.ars.persistence.model.Offer;
import cz.muni.fi.pv243.ars.persistence.model.Reservation;
import cz.muni.fi.pv243.ars.repository.ReservationRepository;
import cz.muni.fi.pv243.ars.repository.UserRepository;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.time.ZoneId;
import java.util.Date;

@RequestScoped
@Named
public class CreateReservationBean {

    @Inject
    private ReservationRepository reservationRepository;
    @Inject
    private UserRepository userRepository;

    private Date checkInDate;
    private Date checkOutDate;
    private Integer numberOfPeople;

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public String create(Offer offer, Long userId) {
        try {
//            System.out.println("reservation init");
            Reservation reservation = new Reservation();
//            System.out.println("reservation new");
            reservation.setFromDate(checkInDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//            System.out.println("reservation fromDate");
            reservation.setToDate(checkOutDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//            System.out.println("reservation toDate");
            reservation.setNumberOfPeople(numberOfPeople);
//            System.out.println("reservation people");
            reservation.setOffer(offer);
//            System.out.println("reservation offer, userId: " + userId);
            reservation.setUser(userRepository.findById(userId));
//            System.out.println("reservation user");
            reservationRepository.create(reservation);
//            System.out.println("reservation create");
            return "reservations";
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("Something went wrong, please try again later.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "offers";
        }
    }
}
