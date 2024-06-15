package com.bravo.carrental.reservation.model;

import com.bravo.carrental.auth.api.model.User;
import com.bravo.carrental.car.model.Car;
import com.bravo.carrental.car.model.CarCreationValidation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date", nullable = false)
    private String start_date;
    @Column(name = "end_date", nullable = false)
    private String end_date;

    @ManyToOne
    private User user;

    @ManyToOne
    private Car car;

    public Long getId() {
        return id;}



    public @NotBlank(message = "Starting date is mandatory", groups = CarCreationValidation.class) String getStartDate() {
        return String.valueOf(start_date);}

    public @NotBlank(message = "End date is mandatory", groups = CarCreationValidation.class) String getEndDate() {
        return String.valueOf(end_date);}

    public User getUser() {
        return user;}


    public Car getCar() {
        return car;}
}