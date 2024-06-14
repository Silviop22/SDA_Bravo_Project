package com.bravo.carrental.reservation.repository;

import com.bravo.carrental.reservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByUserId(Long userid);
    List<Reservation> findByCarId(Long car_id);
    @Query("SELECT r FROM Reservation r WHERE r.car = :carId AND " +
            "(:startDate BETWEEN r.start_date AND r.end_date OR " +
            ":endDate BETWEEN r.start_date AND r.end_date OR " +
            "r.start_date BETWEEN :startDate AND :endDate OR " +
            "r.end_date BETWEEN :start_date AND :end_date)")
    List<Reservation> findReservationsForCarBetweenDates(@Param("carId") Long carId,
                                                         @Param("startDate") LocalDate startDate,
                                                         @Param("endDate") LocalDate endDate);

}