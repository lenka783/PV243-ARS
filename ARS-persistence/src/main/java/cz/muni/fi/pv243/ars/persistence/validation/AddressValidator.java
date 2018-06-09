package cz.muni.fi.pv243.ars.persistence.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import cz.muni.fi.pv243.ars.persistence.model.Address;

/**
 * Created by jsmolar on 5/17/18.
 */
public class AddressValidator implements ConstraintValidator<AddressConstraint, Address> {
    public void initialize(AddressConstraint constraintAnnotation) {

    }

    public boolean isValid(Address value, ConstraintValidatorContext context) {
        return true;
    }
}
