package cz.muni.fi.pv243.ars.persistance.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Payload;

/**
 * Created by jsmolar on 5/17/18.
 */
@javax.validation.Constraint(validatedBy = { AddressValidator.class })
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface AddressConstraint {

    String message() default "AddressConstraint Fields must not be null/empty and obey character limit constraints";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
