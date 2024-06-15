package com.bravo.carrental.reservation.model;

import com.bravo.carrental.auth.api.model.User;
import com.bravo.carrental.car.model.Car;
import com.bravo.carrental.car.model.CarCreationValidation;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jdk.jshell.Snippet;
import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class ReservationDto {

    @Id
    private Long id;

    @NotBlank(message = "Starting date is mandatory", groups = CarCreationValidation.class)
    private String start_date;

    @NotBlank(message = "End date is mandatory", groups = CarCreationValidation.class)
    private String end_date;

    @NotBlank(message = "User is mandatory", groups = ReservationValidation.class)
    private User user;

    @NotBlank(message ="Car is mandatory", groups = ReservationValidation.class)
    private Car car;
}