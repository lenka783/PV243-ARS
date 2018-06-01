package cz.muni.fi.pv243;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import cz.muni.fi.pv243.ars.enumeration.UserRole;
import cz.muni.fi.pv243.ars.model.Address;
import cz.muni.fi.pv243.ars.model.User;
import cz.muni.fi.pv243.ars.repository.UserRepository;
import cz.muni.fi.pv243.ars.utils.Resources;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by jsmolar on 5/28/18.
 */
@RunWith(Arquillian.class)
public class UserManagerTest {

    @Inject
    private EntityManager entityManager;

    @Inject
    private UserRepository userManager;

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap
            .create(WebArchive.class)
            .addClass(Resources.class)
            .addPackages(true, "cz.muni.fi.pv243.ars.repository", "cz.muni.fi.pv243.ars.model")
            .addPackage(UserRole.class.getPackage())
            .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void createUserTest() {
        User expected = new User();
        Address address = new Address();

        expected.setName("James").setSurname("Loys").setEmail("james@example.com").setPassword("123456").setAddress(address);
        userManager.create(expected);

        User actual = entityManager.find(User.class, expected.getId());
        assertEquals(expected, actual);
    }
}
