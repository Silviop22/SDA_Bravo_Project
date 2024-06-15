package com.bravo.carrental.reservation.controller;

import com.bravo.carrental.reservation.exception.DateException;
import com.bravo.carrental.reservation.model.ReservationDto;
import com.bravo.carrental.reservation.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<ReservationDto>> getItems() {
        logger.info("Fetching all reservations");
        try {
            return ResponseEntity.ok(reservationService.getReservations());
        } catch (Exception e) {
            logger.error("Error fetching reservations: ", e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> getItemById(@PathVariable Long id) {
        logger.info("Fetching reservation with ID: {}", id);
        try {
            return ResponseEntity.ok(reservationService.getById(id));
        } catch (Exception e) {
            logger.error("Error fetching reservation: ", e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<String> createReservation(@RequestBody ReservationDto reservationDto) {
        try {
            logger.info("Creating reservation for car ID: {} and user ID: {}", reservationDto.getCar().getId(), reservationDto.getUser().getId());
            reservationService.create(reservationDto);
            return ResponseEntity.ok("Reservation created successfully");
        } catch (DateException e) {
            logger.error("DateException: ", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error creating reservation: ", e);
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateReservation(@RequestBody ReservationDto reservationDto, @PathVariable Long id) {
        try {
            logger.info("Updating reservation with ID: {}", id);
            reservationService.update(reservationDto, id);
            return ResponseEntity.ok("Reservation updated successfully");
        } catch (DateException e) {
            logger.error("DateException: ", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error updating reservation: ", e);
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable Long id) {
        try {
            logger.info("Deleting reservation with ID: {}", id);
            reservationService.deleteById(id);
            return ResponseEntity.ok("Reservation deleted successfully");
        } catch (Exception e) {
            logger.error("Error deleting reservation: ", e);
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

    @GetMapping("/cars/{carId}")
    public ResponseEntity<List<ReservationDto>> getReservationsByCarId(@PathVariable Long carId) {
        logger.info("Fetching reservations for car ID: {}", carId);
        try {
            return ResponseEntity.ok(reservationService.getByCarId(carId));
        } catch (Exception e) {
            logger.error("Error fetching reservations: ", e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<ReservationDto>> getReservationsByUserId(@PathVariable Long userId) {
        logger.info("Fetching reservations for user ID: {}", userId);
        try {
            return ResponseEntity.ok(reservationService.getByUserid(userId));
        } catch (Exception e) {
            logger.error("Error fetching reservations: ", e);
            return ResponseEntity.status(500).body(null);
        }
    }
}
