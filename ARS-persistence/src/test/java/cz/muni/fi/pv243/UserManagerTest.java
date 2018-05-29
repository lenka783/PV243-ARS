package cz.muni.fi.pv243;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.muni.fi.pv243.ars.manager.UserManager;
import cz.muni.fi.pv243.ars.model.User;
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

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private UserManager userManager;

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap
            .create(WebArchive.class)
            .addClass(User.class)
            .addAsResource("META-INF/test-persistence.xml");
    }

    @Test
    public void createUserTest() {
        User expected = new User();
        expected.setName("James").setSurname("Loys").setEmail("james@example.com");
        userManager.create(expected);

        User actual = entityManager.find(User.class, expected.getId());
        assertEquals(expected, actual);
    }
}
