package cz.muni.fi.pv243.utils;

import java.time.LocalDate;
import java.util.Random;

import cz.muni.fi.pv243.ars.persistance.enumeration.AccommodationType;
import cz.muni.fi.pv243.ars.persistance.model.Address;
import cz.muni.fi.pv243.ars.persistance.model.Offer;
import cz.muni.fi.pv243.ars.persistance.model.Reservation;
import cz.muni.fi.pv243.ars.persistance.model.User;

/**
 * Created by jsmolar on 6/1/18.
 */
public class EntityFactoryPersistence {

    Random rand = new Random();

    public User createUser(String name) {
        User user = new User();
        user.setName(name)
            .setSurname("surname")
            .setDateOfBirth(LocalDate.now())
            .setPassword("123456")
            .setEmail(name + "@example.com");

        return user;
    }

    public User createUser(String name, Address address) {
        return createUser(name).setAddress(address);
    }

    public Address createAddress(String city, String country, String street) {
        Address address = new Address();

        address.setCity(city)
            .setCountry(country)
            .setStreet(street)
            .setPostCode(String.valueOf(rand.nextInt(50)));

        return address;
    }

    public Offer createOffer(Address address) {
        Offer offer = new Offer();

        offer.setAccommodationType(AccommodationType.HOUSE)
            .setAddress(address)
            .setAnimalFriendly(true)
            .setCapacity(rand.nextInt(10));

        return offer;
    }

    public Reservation createReservation(LocalDate from, LocalDate to, Offer offer) {
        Reservation reservation = new Reservation();

        reservation.setFrom(from)
            .setTo(to)
            .setNumberOfPeople(rand.nextInt(10))
            .setOffer(offer);

        return reservation;
    }

}
