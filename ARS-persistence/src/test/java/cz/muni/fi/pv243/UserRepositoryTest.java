package cz.muni.fi.pv243;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import cz.muni.fi.pv243.ars.persistance.enumeration.UserRole;
import cz.muni.fi.pv243.ars.persistance.model.Address;
import cz.muni.fi.pv243.ars.persistance.model.User;
import cz.muni.fi.pv243.ars.repository.AddressRepository;
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

    @Inject
    private AddressRepository addressRepository;

    private EntityFactoryPersistence ef = new EntityFactoryPersistence();

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap
            .create(WebArchive.class)
            .addClasses(Resources.class, EntityFactoryPersistence.class)
            .addPackages(true, "cz.muni.fi.pv243.ars.repository", "cz.muni.fi.pv243.ars.persistance.model")
            .addPackage(UserRole.class.getPackage())
            .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    private Address address;

    @Before
    public void init() throws SystemException, NotSupportedException {
        tx.begin();
        address = ef.createAddress("Bratislava", "Slovakia", "Bajkalska");
        User user1 = ef.createUser("user1Test", address);
        User user2 = ef.createUser("user2Test", address);
        User user3 = ef.createUser("user3Test", address);

        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.persist(user3);
    }

    @After
    public void tearDown() throws SystemException {
        tx.rollback();
    }

    @Test
    public void createUserTest() {
        User expected = ef.createUser("testUser", address);

        userRepository.create(expected);

        User actual = entityManager.find(User.class, expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    public void updateUserTest() {
        User expected = ef.createUser("updateUser", address);
        entityManager.persist(expected);
        assertEquals(entityManager.find(User.class, expected.getId()), expected);

        expected.setEmail("updatedTestEmail@example.com");
        userRepository.update(expected);

        User actual = entityManager.find(User.class, expected.getId());
        assertEquals(expected, actual);
        assertEquals(expected.getEmail(), actual.getEmail());
    }

    @Test
    public void deleteUserTest() {
        User user = ef.createUser("userForDelete");
        entityManager.persist(user);

        int userCount = userRepository.findAll().size();
        userRepository.delete(user);

        assertEquals(userRepository.findById(user.getId()), null);
        assertEquals(userCount - 1, userRepository.findAll().size());
    }
}
