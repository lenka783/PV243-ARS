package cz.muni.fi.pv243.ars.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateRangeValidator.class)
@Documented
public @interface ReservationDateRange {

    //TODO: repair annotation, it's not working correctly

    String message() default "Reservation's date field 'from' must be before date field 'to'.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
