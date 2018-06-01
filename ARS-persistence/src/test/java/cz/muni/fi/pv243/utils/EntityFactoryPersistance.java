package cz.muni.fi.pv243.utils;

import java.time.LocalDate;
import java.util.Random;

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

    public Address createAddress(String city, String country, String street) {
        Address address = new Address();
        Random rand = new Random();

        address.setCity(city)
            .setCountry(country)
            .setStreet(street)
            .setPostCode(String.valueOf(rand.nextInt(50)));

        return address;
    }

    public Offer createOffer() {
        Offer offer = new Offer();

        return offer;
    }

    public Reservation createReservation() {
        Reservation reservation = new Reservation();

        return reservation;
    }

}
