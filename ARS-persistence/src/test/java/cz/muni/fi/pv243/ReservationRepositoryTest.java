package cz.muni.fi.pv243;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.EJBTransactionRolledbackException;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.*;

import cz.muni.fi.pv243.ars.persistence.enumeration.UserRole;
import cz.muni.fi.pv243.ars.persistence.model.Address;
import cz.muni.fi.pv243.ars.persistence.model.Offer;
import cz.muni.fi.pv243.ars.persistence.model.Reservation;
import cz.muni.fi.pv243.ars.persistence.model.User;
import cz.muni.fi.pv243.ars.repository.AddressRepository;
import cz.muni.fi.pv243.ars.repository.OfferRepository;
import cz.muni.fi.pv243.ars.repository.ReservationRepository;
import cz.muni.fi.pv243.ars.repository.UserRepository;
import cz.muni.fi.pv243.ars.utils.Resources;
import cz.muni.fi.pv243.utils.EntityFactoryPersistence;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.spi.ArquillianProxyException;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;


@RunWith(Arquillian.class)
public class ReservationRepositoryTest {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    @Inject
    private UserTransaction tx;

    @Inject
    private UserRepository userRepository;

    @Inject
    private ReservationRepository reservationRepository;

    @Inject
    private OfferRepository offerRepository;

    @Inject
    private AddressRepository addressRepository;

    private EntityFactoryPersistence ef = new EntityFactoryPersistence();

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap
                .create(WebArchive.class)
                .addClasses(Resources.class, EntityFactoryPersistence.class)
                .addPackages(true, "cz.muni.fi.pv243.ars.repository", "cz.muni.fi.pv243.ars.persistence")
                .addPackage(UserRole.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    private User tenant1;
    private User tenant2;
    private User host;
    private Offer offer1;
    private Offer offer2;

    @Before
    public void init() throws SystemException, NotSupportedException {
        tx.begin();

        Address tenant1Address = ef.createAddress("Brno", "CR", "Botanicka");
        Address tenant2Address = ef.createAddress("LH", "SR", "Senicka");
        Address offer1Address = ef.createAddress("Praha", "CR", "Main");
        Address offer2Address = ef.createAddress("Bratislava", "SR", "Martincekova");
        Address hostAddress = ef.createAddress("Brno", "CR", "Technologicky park");

        addressRepository.create(tenant1Address);
        addressRepository.create(offer1Address);
        addressRepository.create(offer2Address);
        addressRepository.create(hostAddress);

        tenant1 = ef.createUser("Adam", tenant1Address);
        tenant2 = ef.createUser("Peter", tenant2Address);
        host = ef.createUser("John", hostAddress);

        userRepository.create(tenant1);
        userRepository.create(tenant2);
        userRepository.create(host);

        offer1 = ef.createOffer(offer1Address, tenant1);
        offer2 = ef.createOffer(offer2Address, tenant2);

        offerRepository.create(offer1);
        offerRepository.create(offer2);
    }

    @After
    public void tearDown() throws SystemException {
        tx.rollback();
    }

    @Test
    public void createReservationTest() {
        Reservation expected = ef.createReservation(
                LocalDate.now(),
                LocalDate.now().plusWeeks(1),
                offer1, host
        );

        offer1.addReservation(expected);
        host.addReservation(expected);
        reservationRepository.create(expected);

        Reservation actual = entityManager.find(Reservation.class, expected.getId());
        assertEquals(expected, actual);
        assertTrue(entityManager.find(User.class, expected.getUser().getId()).getReservations().contains(expected));
    }

    //@Test(expected = ArquillianProxyException.class)
    public void createReservationWithoutHostTest() {
        Reservation res = ef.createReservation(
                LocalDate.now(),
                LocalDate.now().plusWeeks(1),
                offer1
        );
        reservationRepository.create(res);
    }

    @Test(expected = ArquillianProxyException.class)
    public void createReservationWithoutOfferTest() {
        Reservation res = ef.createReservation(
                LocalDate.now(),
                LocalDate.now().plusWeeks(1),
                null, host
        );
        reservationRepository.create(res);
    }

    @Test(expected = ArquillianProxyException.class)
    public void createReservationWithStartDateAfterEndDateTest() {
        Reservation res = ef.createReservation(
                LocalDate.now().plusWeeks(1),
                LocalDate.now(),
                offer1, host
        );
        reservationRepository.create(res);
    }

    @Test
    public void updateDateReservationTest() {
        Reservation expected = ef.createReservation(
                LocalDate.now().plusWeeks(1),
                LocalDate.now().plusWeeks(2),
                offer1, host
        );
        reservationRepository.create(expected);

        assertEquals(expected, reservationRepository.findById(expected.getId()));

        expected.setFromDate(LocalDate.now());
        expected = reservationRepository.update(expected);

        Reservation actual = reservationRepository.findById(expected.getId());
        assertEquals(expected, actual);
        assertEquals(expected.getFromDate(), actual.getFromDate());
    }

    @Test
    public void deleteReservationTest() {
        Reservation expected = ef.createReservation(
                LocalDate.now().plusWeeks(1),
                LocalDate.now().plusWeeks(2),
                offer1, host
        );
        offer1.addReservation(expected);
        host.addReservation(expected);
        reservationRepository.create(expected);
        int reservationCount = reservationRepository.findAll().size();
        int reservationForUserCount = reservationRepository.findAllForUser(expected.getUser()).size();

        reservationRepository.delete(expected);

        Reservation actual = reservationRepository.findById(expected.getId());
        assertEquals(null, actual);
        assertEquals(reservationCount - 1, reservationRepository.findAll().size());
        assertEquals(reservationForUserCount - 1, reservationRepository.findAllForUser(host).size());
    }

    @Test
    public void findReservationByIdTest() {
        Reservation expected = ef.createReservation(
                LocalDate.now().plusWeeks(1),
                LocalDate.now().plusWeeks(2),
                offer1, host
        );
        reservationRepository.create(expected);

        assertEquals(expected, reservationRepository.findById(expected.getId()));
    }

    @Test
    public void findAllReservationsTest() {
        int reservationsCount = reservationRepository.findAll().size();
        Reservation expected = ef.createReservation(
                LocalDate.now().plusWeeks(1),
                LocalDate.now().plusWeeks(2),
                offer1, host
        );
        reservationRepository.create(expected);
        List<Reservation> reservations = reservationRepository.findAll();

        assertEquals(reservationsCount + 1, reservations.size());
        assertTrue(reservations.contains(expected));
    }

    @Test
    public void findAllReservationsForUserTest() {
        Reservation res1 = ef.createReservation(
                LocalDate.now().plusWeeks(1),
                LocalDate.now().plusWeeks(2),
                offer1, host
        );
        Reservation res2 = ef.createReservation(
                LocalDate.now().plusWeeks(1),
                LocalDate.now().plusWeeks(2),
                offer2, tenant1
        );
        offer1.addReservation(res1);
        host.addReservation(res1);
        offer2.addReservation(res2);
        tenant2.addReservation(res2);
        reservationRepository.create(res1);
        reservationRepository.create(res2);

        List<Reservation> reservations = reservationRepository.findAllForUser(host);

        assertEquals(1, reservations.size());
        assertTrue(reservations.contains(res1));
        assertFalse(reservations.contains(res2));
    }
}
