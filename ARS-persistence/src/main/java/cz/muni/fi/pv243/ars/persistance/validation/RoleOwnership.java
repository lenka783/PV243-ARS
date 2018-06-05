package cz.muni.fi.pv243.ars.persistance.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Created by jsmolar on 6/5/18.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {RolesOwnershipValidator.class})
public @interface RoleOwnership {
    String message () default "total price must be 50 or greater for online order. " +
        "Found: ${validatedValue.totalPrice}";
    Class<?>[] groups () default {};
    Class<? extends Payload>[] payload () default {};
}
