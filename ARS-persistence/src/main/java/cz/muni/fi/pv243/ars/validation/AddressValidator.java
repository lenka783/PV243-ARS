package cz.muni.fi.pv243.ars.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by jsmolar on 5/17/18.
 */
public class AddressValidator implements ConstraintValidator<AddressConstraint, AddressConstraint> {
    public void initialize(AddressConstraint constraintAnnotation) {

    }

    public boolean isValid(AddressConstraint value, ConstraintValidatorContext context) {
        return false;
    }
}
