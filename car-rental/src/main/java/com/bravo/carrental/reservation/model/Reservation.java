package com.bravo.carrental.reservation.model;

import com.bravo.carrental.auth.api.model.User;
import com.bravo.carrental.car.model.Car;
import jakarta.persistence.*;
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
    @Column(name = "status", nullable = false)
    private Enum status;
    @Column(name = "start_date", nullable = false)
    private String startDate;
    @Column(name = "end_date", nullable = false)
    private String endDate;

    @ManyToOne
    private User user;

    @ManyToOne
    private Car car;

    public Long getId() {
        return id;}
}