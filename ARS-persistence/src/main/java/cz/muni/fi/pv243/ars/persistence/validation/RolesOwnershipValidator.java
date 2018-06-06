package cz.muni.fi.pv243.ars.persistence.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import cz.muni.fi.pv243.ars.persistence.model.User;

/**
 * Created by jsmolar on 6/5/18.
 */
public class RolesOwnershipValidator implements ConstraintValidator<RoleOwnership, User> {

    @Override
    public void initialize(RoleOwnership constraintAnnotation) {

    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {
        System.out.println("Log message validator " + user.getName());
        return true;
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
