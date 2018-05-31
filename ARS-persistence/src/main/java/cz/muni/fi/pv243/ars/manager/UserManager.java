package cz.muni.fi.pv243.ars.manager;

import java.util.List;

import cz.muni.fi.pv243.ars.model.User;

/**
 * Created by jsmolar on 5/28/18.
 */
public interface UserManager {

    void create(User user);

    User update(User user);

    void delete(User user);

    User findById(long id);

    List<User> findAll();

}
