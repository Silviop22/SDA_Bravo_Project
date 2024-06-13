package com.bravo.carrental.reservation.model;

import com.bravo.carrental.car.model.Car;
import com.bravo.carrental.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;

@Entity
@Builder
public class Reservation {
    @Id
    private Long id;

    private Enum status;

    private String start_date;

    private String end_date;

    @ManyToOne
    private User user;

    @ManyToOne
    private Car car;


    public Reservation(Car car, String end_date, Long id, String start_date,
                       Enum status, User user) {
        this.car = car;
        this.end_date = end_date;
        this.id = id;
        this.start_date = start_date;
        this.status = status;
        this.user = user;
    }

    public Reservation(){}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public Enum getStatus() {
        return status;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public User getUser() {
        return user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public void setStatus(Enum status) {
        this.status = status;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
