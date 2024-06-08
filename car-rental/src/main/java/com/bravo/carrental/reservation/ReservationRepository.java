package com.bravo.carrental.reservation;

import com.bravo.carrental.car.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT MONTH(r.startDate), r FROM Reservation r GROUP BY MONTH(r.startDate)")
    List<Object[]> findReservationsGroupedByMonth();

    @Query("SELECT r.car " +
            "FROM Reservation r " +
            "WHERE r.car.id = (SELECT r2.car.id " +
            "FROM Reservation r2 " +
            "WHERE MONTH(r2.startDate) = :month " +
            "GROUP BY r2.car " +
            "ORDER BY COUNT(r2) " +
            "DESC)")
    Optional<Car> findMostUsedCarInMonth(@Param("month") int month);


    @Query("SELECT r.car " +
            "FROM Reservation r " +
            "WHERE r.car.id = (SELECT r2.car.id " +
            "FROM Reservation r2 " +
            "WHERE MONTH(r2.startDate) = :month " +
            "GROUP BY r2.car " +
            "ORDER BY COUNT(r2) " +
            "ASC)")
    Optional<Car> findLeaseUsedCarInMonth(@Param("month") int month);

    @Query("SELECT SUM(r.totalPrice) " +
            "FROM Reservation r" +
            " WHERE MONTH(r.startDate) = :month")
    BigDecimal findTotalIncomeByMonth(@Param("month") int month);
}
