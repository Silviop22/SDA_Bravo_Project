package com.bravo.carrental.reservation.model;

import com.bravo.carrental.car.model.ResourceCreationValidation;
import com.bravo.carrental.reservation.exception.DateException;

public interface ReservationValidation extends ResourceCreationValidation {
    void validateReservationCreation(ReservationDto carDto) throws DateException;
}
