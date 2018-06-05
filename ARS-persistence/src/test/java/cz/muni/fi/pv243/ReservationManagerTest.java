package cz.muni.fi.pv243;

import cz.muni.fi.pv243.ars.enumeration.AccomodationType;
import cz.muni.fi.pv243.ars.enumeration.UserRole;
import cz.muni.fi.pv243.ars.manager.impl.OfferManagerImpl;
import cz.muni.fi.pv243.ars.manager.impl.ReservationManagerImpl;
import cz.muni.fi.pv243.ars.manager.impl.UserManagerImpl;
import cz.muni.fi.pv243.ars.model.Address;
import cz.muni.fi.pv243.ars.model.Offer;
import cz.muni.fi.pv243.ars.model.Reservation;
import cz.muni.fi.pv243.ars.model.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class ReservationManagerTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private ReservationManagerImpl reservationManager;

    private User tenant;
    private User host;
    private Offer offer;

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap
                .create(WebArchive.class)
                .addPackages(true, "cz.muni.fi.pv243.ars.manager", "cz.muni.fi.pv243.ars.model")
                .addPackage(UserRole.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Before
    public void init() {
        Address tenantAddress = new Address(
                "Botanicka",
                "Brno",
                "CR",
                "",
                "61800").setId(23l);
        Address offerAddress = new Address(
                "Main",
                "Praha",
                "CR",
                "",
                "92339").setId(2342l);
        Address hostAddress = new Address(
                "Technologicka",
                "Brno",
                "CR",
                "",
                "23984").setId(42l);

        tenant = new User(
                "Adam",
                "Black",
                "adam.black@mail.com",
                "12345667",
                tenantAddress,
                LocalDate.now()
        );
        tenant.setId(190l);
        tenantAddress.setUser(tenant);

        host = new User(
                "Adam",
                "Black",
                "adam.black@mail.com",
                "12345667",
                hostAddress,
                LocalDate.now()
        );
        host.setId(19023l);
        hostAddress.setUser(host);

        offer = new Offer(
                offerAddress,
                5,
                AccomodationType.APARTMENT,
                true,
                false,
                tenant
        );
        offerAddress.setUser(tenant);

        offer.setId(12309l);
    }
    /**public void init() {
        Address tenantAddress = EntityFactoryPersistance.createAddress(
                "Brno",
                "CR",
                "Botanicka");
        Address offerAddress = EntityFactoryPersistance.createAddress(
                "Praha",
                "CR",
                "Main");
        tenant = EntityFactoryPersistance.createUser(
                "Adam",
                "Black",
                tenantAddress,
                LocalDate.now(),
                "12345667",
                "adam.black@mail.com");

        offer = EntityFactoryPersistance.createOffer(
                offerAddress, tenant, 5, AccomodationType.APARTMENT, false, true);
    }*/

    @Test
    public void createReservationTest() {
        Reservation expected = new Reservation(
                offer,
                host,
                Date.valueOf("2018-06-16"),
                Date.valueOf("2018-06-28"),
                4);

        reservationManager.create(expected);

        Reservation actual = entityManager.find(Reservation.class, expected.getId());
        assertEquals(expected, actual);
    }
}
