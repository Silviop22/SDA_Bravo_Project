package com.bravo.carrental.reservation.model;

import com.bravo.carrental.car.model.Car;
import com.bravo.carrental.car.model.CarCreationValidation;
import com.bravo.carrental.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class ReservationDto {

    @Id
    private Long id;

    @NotBlank(message = "Status is mandatory", groups = ReservationValidation.class)
    private Enum status;

    @NotBlank(message = "Starting date is mandatory", groups = CarCreationValidation.class)
    private String start_date;

    @NotBlank(message = "End date is mandatory", groups = CarCreationValidation.class)
    private String end_date;

    @NotBlank(message = "User is mandatory", groups = ReservationValidation.class)
    private User user;

    @NotBlank(message ="Car is mandatory", groups = ReservationValidation.class)
    private Car car;


}