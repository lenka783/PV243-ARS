package cz.muni.fi.pv243.ars.persistance.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import cz.muni.fi.pv243.ars.persistance.model.User;

/**
 * Created by jsmolar on 6/5/18.
 */
public class RolesOwnershipValidator implements ConstraintValidator<RoleOwnership, User> {

    @Override
    public void initialize(RoleOwnership constraintAnnotation) {

    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {
        return false;
//        Set<UserRole> roles = user.getRoles();
//        if (roles.contains(UserRole.TENANT) && user.getReservations() != null) {
//            return false;
//        }
//        if (roles.contains(UserRole.HOST) && user.getOffers() != null) {
//            return false;
//        }
//
//        return true;
    }
}
