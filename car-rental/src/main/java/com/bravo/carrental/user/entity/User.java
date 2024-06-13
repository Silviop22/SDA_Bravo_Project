package com.bravo.carrental.user.entity;

import com.bravo.carrental.car.model.Car;
import com.bravo.carrental.reservation.model.Reservation;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private UserRole userRole;

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "car")
    private List<Car> cars;

}
