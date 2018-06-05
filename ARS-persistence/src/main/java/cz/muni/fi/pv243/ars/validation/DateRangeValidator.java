package cz.muni.fi.pv243.ars.validation;

import cz.muni.fi.pv243.ars.model.Reservation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class DateRangeValidator implements ConstraintValidator<ReservationDateRange, Reservation> {

    public void initialize(ReservationDateRange constraintAnnotation) {

    }

    //TODO: finnish this validation, it can't be used correctly
    public boolean isValid(Reservation value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        Date from = value.getFrom();
        Date to = value.getTo();

        boolean isValid;
        if (from.compareTo(to) > 0) {
            isValid = true;
        } else {
            isValid = false;
        }

        if ( !isValid ) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "{context.ReservationDateRange.message}"
            )
                    .addConstraintViolation();
        }

        return isValid;
    }
}
