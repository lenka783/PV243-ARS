package cz.muni.fi.pv243.ars.persistence.validation;

import java.util.Set;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import cz.muni.fi.pv243.ars.persistence.enumeration.UserRole;
import cz.muni.fi.pv243.ars.persistence.model.User;

/**
 * Created by jsmolar on 6/5/18.
 */
public class RolesOwnershipValidator implements ConstraintValidator<RoleOwnership, User> {

    @Inject
    private Logger log;

    @Override
    public void initialize(RoleOwnership constraintAnnotation) {

    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {
        Set<UserRole> roles = user.getRoles();
        if (roles.contains(UserRole.TENANT) && !user.getOffers().isEmpty()) {
            log.warning("Tenant can't own Offers");
            return false;
        }
        if (roles.contains(UserRole.HOST) && !user.getReservations().isEmpty()) {
            log.warning("Host can't own Reservations");
            return false;
        }

        return true;
    }
}
