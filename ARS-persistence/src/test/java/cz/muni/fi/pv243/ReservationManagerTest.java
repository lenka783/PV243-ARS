package cz.muni.fi.pv243;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import cz.muni.fi.pv243.ars.persistence.enumeration.AccommodationType;
import cz.muni.fi.pv243.ars.persistence.enumeration.UserRole;
import cz.muni.fi.pv243.ars.persistence.model.Address;
import cz.muni.fi.pv243.ars.persistence.model.Offer;
import cz.muni.fi.pv243.ars.persistence.model.Reservation;
import cz.muni.fi.pv243.ars.persistence.model.User;
import cz.muni.fi.pv243.ars.repository.AddressRepository;
import cz.muni.fi.pv243.ars.repository.ReservationRepository;
import cz.muni.fi.pv243.ars.repository.UserRepository;
import cz.muni.fi.pv243.ars.utils.Resources;
import cz.muni.fi.pv243.utils.EntityFactoryPersistence;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(Arquillian.class)
public class ReservationManagerTest {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    @Inject
    private ReservationRepository reservationRepository;

    @Inject
    private UserTransaction tx;

    @Inject
    private UserRepository userRepository;

    @Inject
    private AddressRepository addressRepository;

    private EntityFactoryPersistence ef = new EntityFactoryPersistence();

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap
                .create(WebArchive.class)
                .addClasses(Resources.class, EntityFactoryPersistence.class)
                .addPackages(true, "cz.muni.fi.pv243.ars.repository", "cz.muni.fi.pv243.ars.persistence.model")
                .addPackage(UserRole.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    private User tenant;
    private User host;
    private Offer offer;

    @Before
    public void init() throws SystemException, NotSupportedException {
        tx.begin();
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
                AccommodationType.APARTMENT,
                true,
                false,
                tenant);
        offerAddress.setUser(tenant);

        offer.setId(12309l);
    }

    @After
    public void tearDown() throws SystemException {
        tx.rollback();
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
                LocalDate.of(2018,6, 16),
                LocalDate.of(2018, 6, 28),
                4);

        reservationRepository.create(expected);

        Reservation actual = entityManager.find(Reservation.class, expected.getId());
        assertEquals(expected, actual);
    }
}
