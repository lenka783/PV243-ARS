package cz.muni.fi.pv243.ars.persistence.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ReservationDateRangeValidator.class})
public @interface ReservationDateRangeConstraint {

    String message () default "Validation of Reservation failed for some very unknown reason :D.";
    Class<?>[] groups () default {};
    Class<? extends Payload>[] payload () default {};
}
