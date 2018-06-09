package cz.muni.fi.pv243.ars.persistence.validation;

import cz.muni.fi.pv243.ars.persistence.model.Reservation;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.util.logging.Logger;

public class ReservationDateRangeValidator implements ConstraintValidator<ReservationDateRangeConstraint, Reservation> {

    @Inject
    private Logger log;

    @Override
    public void initialize(ReservationDateRangeConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(Reservation reservation, ConstraintValidatorContext context) {
        LocalDate fromDate = reservation.getFromDate();
        LocalDate toDate = reservation.getToDate();

        if (fromDate.isAfter(toDate)) {
            log.warning("In reservation fromDate must come before toDate.");
            return false;
        }

        return true;
    }
}
