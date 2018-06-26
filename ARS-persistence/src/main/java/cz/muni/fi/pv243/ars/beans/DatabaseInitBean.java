package cz.muni.fi.pv243.ars.beans;

import cz.muni.fi.pv243.ars.persistence.enumeration.AccommodationType;
import cz.muni.fi.pv243.ars.persistence.enumeration.UserRole;
import cz.muni.fi.pv243.ars.persistence.model.Address;
import cz.muni.fi.pv243.ars.persistence.model.Offer;
import cz.muni.fi.pv243.ars.persistence.model.Reservation;
import cz.muni.fi.pv243.ars.persistence.model.User;
import cz.muni.fi.pv243.ars.repository.OfferRepository;
import cz.muni.fi.pv243.ars.repository.ReservationRepository;
import cz.muni.fi.pv243.ars.repository.UserRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.time.LocalDate;

@RequestScoped
@Named
public class DatabaseInitBean {
    public static final String JURAJ_KP = "348c4e83-5242-451c-85d4-7ef317ec46f3";
    public static final String MARIAN_KP = "d805fffe-a3eb-4a9e-a839-7eb556215e3c";
    public static final String ADAM_KP = "a2a46b10-2fcd-44bb-8a4e-0cb7d454c11d";

    private String loadDatabase;

    @Inject
    private OfferRepository offerRepository;

    @Inject
    private ReservationRepository reservationRepository;

    @Inject
    private UserRepository userRepository;

    public String getLoadDatabase() {
        return "Database was succesfully loadDatabase";
    }

    @PostConstruct
    public void init() throws IOException {
        Address address0 = getAddress("Vajnorska","Bratislava",null,"Slovakia","99999");
        Address address1 = getAddress("P.O. Box 629, 3146 Orci, Street","Gatineau","QC","Kenya","3059");
        Address address2 = getAddress("451-8102 Vel Ave","Columbus","OH","Kuwait","09251");
        Address address3 = getAddress("4953 Convallis, Rd.","San Rafael","A","Trinidad and Tobago","3293");
        Address address4 = getAddress("401-8099 Lorem St.","Napier","NI","Montenegro","884123");
        Address address5 = getAddress("P.O. Box 154, 7903 Mauris Rd.","Quinta de Tilcoco","OHiggins","Pitcairn Islands","JE68 5CC");
        Address address6 = getAddress("P.O. Box 588, 7787 Risus, Rd.","Idaho Falls","Idaho","Yemen","84050-795");
        Address address7 = getAddress("8987 Eget Rd.","Boo","Stockholms län","Sweden","XU92 6EP");

        User userTenantHost = getUser(MARIAN_KP, "Marian", "Dolny", "marian.dolny@ars.cz", "admin", true,LocalDate.of(1997, 6, 21),address0);
        User userTenant     = getUser(ADAM_KP, "Adam", "Horny", "adam.horny@ars.cz", "admin", true,LocalDate.of(1990, 8, 1),address1);
        User userHost       = getUser(JURAJ_KP, "Juraj", "Stredny", "juraj.stredny@ars.cz", "admin", true,LocalDate.of(1979, 10, 30),address2);
        userTenantHost.addRole(UserRole.TENANT).addRole(UserRole.HOST);
        userHost.addRole(UserRole.HOST);
        userTenant.addRole(UserRole.TENANT);
        userRepository.create(userTenantHost);
        userRepository.create(userTenant);
        userRepository.create(userHost);
        System.out.println("Users ids: host - " + userHost.getId() + "tenant - " + userTenant.getId() + "hostTenant - " + userTenantHost.getId());

        
        Offer offer0 = getOffer("Best vacation ever!",4,AccommodationType.APARTMENT,false,true,address3, userHost,2700);
        Offer offer1 = getOffer("Romantic holiday",9,AccommodationType.HOUSE,true,true,address4,userTenantHost,3750);
        Offer offer2 = getOffer("Family chill-out",8,AccommodationType.HOUSE,true,true,address5,userTenantHost,3000);
        Offer offer3 = getOffer("Best price in town",2,AccommodationType.ROOM,true,false,address6,userHost,350);
        Offer offer4 = getOffer("Student home",1,AccommodationType.ROOM,false,false,address7,userTenantHost,300);

        offerRepository.create(offer0);
        offerRepository.create(offer1);
        offerRepository.create(offer2);
        offerRepository.create(offer3);
        offerRepository.create(offer4);


        Reservation reservation0 = getReservation(LocalDate.of(2018,7,31),LocalDate.of(2018, 8, 12),
                8,offer1,userTenant);
        Reservation reservation1 = getReservation(LocalDate.of(2018, 6, 30),LocalDate.of(2018, 7, 25),
                1,offer0,userTenantHost);
        Reservation reservation2 = getReservation(LocalDate.of(2018, 4, 30),LocalDate.of(2018, 5, 25),
                4,offer1,userTenant);
        Reservation reservation3 = getReservation(LocalDate.of(2018, 9, 20),LocalDate.of(2018, 9, 25),
                1,offer3,userTenantHost);
        Reservation reservation4 = getReservation(LocalDate.of(2018, 9, 30),LocalDate.of(2018, 10, 25),
                1,offer3,userTenant);
        Reservation reservation5 = getReservation(LocalDate.of(2018, 4, 30),LocalDate.of(2018, 5, 25),
                2,offer2,userTenant);

        reservationRepository.create(reservation0);
        reservationRepository.create(reservation1);
        reservationRepository.create(reservation2);
        reservationRepository.create(reservation3);
        reservationRepository.create(reservation4);
        reservationRepository.create(reservation5);

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("index.jsf");
    }

    private Address getAddress(String street, String city, String state, String country, String postCode) {
        Address address = new Address();
        address.setStreet(street)
                .setCity(city)
                .setState(state)
                .setCountry(country)
                .setPostCode(postCode);
        return address;
    }

    private User getUser(
            String keycloakPrincipal,
            String name, String surname,
            String email, String password,
            Boolean isActive, LocalDate dateOfBirth,
            Address address) {
        User user = new User();
        user.setKeycloakPrincipal(keycloakPrincipal)
                .setName(name)
                .setSurname(surname)
                .setEmail(email)
                .setPassword(password)
                .setActive(isActive)
                .setDateOfBirth(dateOfBirth)
                .setAddress(address);
        return user;
    }

    private Offer getOffer(
            String name, Integer capacity, AccommodationType accommodationType,
            boolean isAnimalFriendly, boolean isSmokerFriendly,
            Address address, User user, Integer price) {
        Offer offer = new Offer();
        offer.setName(name)
                .setCapacity(capacity)
                .setAccommodationType(accommodationType)
                .setAnimalFriendly(isAnimalFriendly)
                .setSmokerFriendly(isSmokerFriendly)
                .setAddress(address)
                .setPrice(price)
                .setUser(user);
        return offer;
    }

    private Reservation getReservation(
            LocalDate fromDate, LocalDate toDate, Integer numberOfPeople,
            Offer offer, User user) {
        Reservation reservation = new Reservation();
        reservation.setFromDate(fromDate)
                .setToDate(toDate)
                .setNumberOfPeople(numberOfPeople)
                .setOffer(offer)
                .setUser(user);
        return reservation;
    }
}
