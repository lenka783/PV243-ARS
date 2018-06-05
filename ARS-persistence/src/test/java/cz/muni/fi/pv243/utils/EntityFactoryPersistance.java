package cz.muni.fi.pv243.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;

import cz.muni.fi.pv243.ars.enumeration.AccomodationType;
import cz.muni.fi.pv243.ars.model.Address;
import cz.muni.fi.pv243.ars.model.Offer;
import cz.muni.fi.pv243.ars.model.Reservation;
import cz.muni.fi.pv243.ars.model.User;

/**
 * Created by jsmolar on 6/1/18.
 */
public class EntityFactoryPersistance {

    public User createUser(String name) {
        User user = new User();
        user.setName(name)
            .setSurname("surname")
            .setDateOfBirth(LocalDate.now())
            .setPassword("123456")
            .setEmail(name + "@example.com");

        return user;
    }

    public static User createUser(String name, String surname, Address address, LocalDate dateOfBirth, String psw, String email) {
        User user = new User();
        user.setName(name)
                .setSurname(surname)
                .setAddress(address)
                .setDateOfBirth(dateOfBirth)
                .setPassword(psw)
                .setEmail(email);

        return user;
    }

    public static Address createAddress(String city, String country, String street) {
        Address address = new Address();
        Random rand = new Random();

        address.setCity(city)
            .setCountry(country)
            .setStreet(street)
            .setPostCode(String.valueOf(rand.nextInt(50)));

        return address;
    }

    public static Offer createOffer(
            Address address, User tenant, int capacity,
            AccomodationType accType, boolean isAnimalFriendly, boolean isSmokerFriendly) {
        Offer offer = new Offer();
        offer.setAddress(address);
        offer.setAccomodationType(accType);
        offer.setAnimalFriendly(isAnimalFriendly);
        offer.setSmokerFriendly(isSmokerFriendly);
        offer.setCapacity(capacity);
        offer.setUser(tenant);

        return offer;
    }

    public static Reservation createReservation(Offer offer, User user, Date from, Date to, int numberOfPeople) {
        Reservation reservation = new Reservation();
        reservation.setOffer(offer);
        reservation.setUser(user);
        reservation.setFrom(from);
        reservation.setTo(to);
        reservation.setNumberOfPeople(numberOfPeople);

        return reservation;
    }

}
