package cz.muni.fi.pv243;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import javax.ejb.EJBTransactionRolledbackException;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import cz.muni.fi.pv243.ars.persistence.enumeration.UserRole;
import cz.muni.fi.pv243.ars.persistence.model.Address;
import cz.muni.fi.pv243.ars.persistence.model.Offer;
import cz.muni.fi.pv243.ars.persistence.model.Reservation;
import cz.muni.fi.pv243.ars.persistence.model.User;
//import cz.muni.fi.pv243.ars.repository.AddressRepository;
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

/**
 * Created by jsmolar on 5/28/18.
 */
@RunWith(Arquillian.class)
public class UserRepositoryTest {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    @Inject
    private UserTransaction tx;

    @Inject
    private UserRepository userRepository;

//    @Inject
//    private AddressRepository addressRepository;

    @Inject
    private OfferRepository offerRepository;

    @Inject
    private ReservationRepository reservationRepository;

    private EntityFactoryPersistence ef = new EntityFactoryPersistence();

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap
            .create(WebArchive.class, "test.war")
            .addClasses(Resources.class, EntityFactoryPersistence.class)
            .addPackages(true, "cz.muni.fi.pv243.ars.repository", "cz.muni.fi.pv243.ars.persistence")
            .addPackage(UserRole.class.getPackage())
            .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    private Address address;
    private Address address1;
    private Address address2;
    private Address address3;

    @Before
    public void init() throws SystemException, NotSupportedException {
        tx.begin();
        address = ef.createAddress("Bratislava", "Slovakia", "Bajkalska");
        address1 = ef.createAddress("Liptovsky Mikulas", "Slovakia", "Senicka");
        address2 = ef.createAddress("Prievidza", "Slovakia", "Hlavna");
        address3 = ef.createAddress("Brno", "CR", "Masarykova");
        User user1 = ef.createUser("user1Test", address);
        User user2 = ef.createUser("user2Test", address1);
        User user3 = ef.createUser("user3Test", address2);

        userRepository.create(user1);
        userRepository.create(user2);
        userRepository.create(user3);
    }

    @After
    public void tearDown() throws SystemException {
        tx.rollback();
    }

    @Test
    public void createUserTest() {
        System.out.println("create test user");
        User expected = ef.createUser("testUser", address3);

        userRepository.create(expected);

        User actual = userRepository.findById(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    public void updateUserTest() {
        System.out.println("update test user");
        User expected = ef.createUser("updateUser", address3);
        userRepository.create(expected);
        assertEquals(userRepository.findById(expected.getId()), expected);

        expected.setEmail("updatedTestEmail@example.com");
        userRepository.update(expected);

        User actual = userRepository.findById(expected.getId());
        assertEquals(expected, actual);
        assertEquals(expected.getEmail(), actual.getEmail());
    }

    @Test
    public void deleteUserTest() {
        System.out.println("delete test user");
        User user = ef.createUser("userForDelete", address3);
        userRepository.create(user);

        int userCount = userRepository.findAll().size();
        userRepository.delete(user);

        assertEquals(userRepository.findById(user.getId()), null);
        assertEquals(userCount - 1, userRepository.findAll().size());
    }

    @Test
    public void findByIdTest() {
        System.out.println("find by id test user");
        User expected = ef.createUser("findUser", address3);
        userRepository.create(expected);

        User actual = userRepository.findById(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    public void findAllTest() {
        System.out.println("find all test user");
        assertEquals(userRepository.findAll().size(), 3);

        User expected = ef.createUser("findAllUser", address3);
        userRepository.create(expected);

        assertEquals(userRepository.findAll().size(), 4);
    }

    @Test
    public void createOfferForHost() {
        User host = ef.createUser("hostUser", address3);
        host.addRole(UserRole.HOST);
        userRepository.create(host);

        Offer offer = ef.createOffer(address, host);
        offerRepository.create(offer);
    }

    @Test
    public void createReservationForTenant() {

    }

    @Test(expected = EJBTransactionRolledbackException.class)
    public void createReservationForHost() {
        User host = ef.createUser("hostUser", address3);
        Offer offer = ef.createOffer(address, host);
        Reservation reservation = ef.createReservation(LocalDate.now(), LocalDate.now().plusWeeks(1), offer, host);

        host.addRole(UserRole.HOST)
            .addReservation(reservation);

        offerRepository.create(offer);
        reservationRepository.create(reservation);
        userRepository.create(host);
    }

    @Test
    public void createOfferForTenant() {

    }

}
