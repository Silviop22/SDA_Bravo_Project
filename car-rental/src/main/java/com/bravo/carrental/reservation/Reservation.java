package com.bravo.carrental.reservation;

import com.bravo.carrental.branch.model.Branch;
import com.bravo.carrental.car.model.Car;
import com.bravo.carrental.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Car car;
    @ManyToOne
    private User customer;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @ManyToOne
    private Branch pickupBranch;
    @ManyToOne
    private Branch dropoffBranch;
    private BigDecimal totalPrice;
    @Enumerated(value = EnumType.STRING)
    private Status status;

    private enum Status{
        PENDING,
        CONFIRMED,
        CANCELLED
    }
}
