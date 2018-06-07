package cz.muni.fi.pv243;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.*;

import cz.muni.fi.pv243.ars.persistence.enumeration.AccommodationType;
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

    private User tenant;
    private User host;
    private Offer offer;

    @Before
    public void init() throws SystemException, NotSupportedException {
        tx.begin();

        Address tenantAddress = ef.createAddress("Brno", "CR", "Botanicka");
        Address offerAddress = ef.createAddress("Praha", "CR", "Main");
        Address hostAddress = ef.createAddress("Brno", "CR", "Technologicky park");

        addressRepository.create(tenantAddress);
        addressRepository.create(offerAddress);
        addressRepository.create(hostAddress);

        tenant = ef.createUser("Adam", tenantAddress);
        host = ef.createUser("John", hostAddress);

        userRepository.create(tenant);
        userRepository.create(host);

        offer = ef.createOffer(offerAddress);

        offerRepository.create(offer);
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
                offer
        );

        reservationRepository.create(expected);

        Reservation actual = entityManager.find(Reservation.class, expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    public void updateReservationTest() {
        Reservation expected = ef.createReservation(
                LocalDate.now().plusWeeks(1),
                LocalDate.now().plusWeeks(2),
                offer
        );
        reservationRepository.create(expected);
        host.addReservation(expected);
        userRepository.update(host);

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
                offer
        );
        reservationRepository.create(expected);
        host.addReservation(expected);
        userRepository.update(host);

        int reservationCountBeforeDelete = reservationRepository.findAllForUser(host).size();

        reservationRepository.delete(expected);

        Reservation actual = reservationRepository.findById(expected.getId());
        assertEquals(null, actual);
        assertEquals(reservationCountBeforeDelete - 1, reservationRepository.findAllForUser(host).size());
    }

    @Test
    public void findReservationByIdTest() {
        Reservation expected = ef.createReservation(
                LocalDate.now().plusWeeks(1),
                LocalDate.now().plusWeeks(2),
                offer
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
    public void findAllReservationsTest() {
        Reservation expected = ef.createReservation(
                LocalDate.now().plusWeeks(1),
                LocalDate.now().plusWeeks(2),
                offer
        );
        reservationRepository.create(expected);
        assertEquals(expected, reservationRepository.findById(expected.getId()));

        expected.setFromDate(LocalDate.now());
        expected = reservationRepository.update(expected);

        Reservation actual = reservationRepository.findById(expected.getId());
        assertEquals(expected, actual);
        assertEquals(expected.getFromDate(), actual.getFromDate());
    }
}
