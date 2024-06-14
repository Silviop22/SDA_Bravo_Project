package com.bravo.carrental.reservation.controller;

import com.bravo.carrental.reservation.exception.DateException;
import com.bravo.carrental.reservation.model.ReservationDto;
import com.bravo.carrental.reservation.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;}

    @GetMapping("/reservations")
    public ResponseEntity<ReservationDto> getItems() {
        return ResponseEntity.ok(reservationService.getReservations());
    }

    @GetMapping("/reservations/{id}")
    public ResponseEntity<ReservationDto> getItemById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getById(id));}

    @PostMapping("/reservations/{id}")
    public void create_Reservation(@RequestBody ReservationDto reservationDto)
            throws DateException {
        reservationService.create(reservationDto);}

    @PatchMapping("/reservations/{id}")
    public void updateCar(@RequestBody ReservationDto reservationDto, @PathVariable
    Long id) throws DateException {
        reservationService.update(reservationDto, id);}

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        reservationService.deleteById(id);
        return ResponseEntity.noContent().build();}

    @GetMapping ("/cars/{carid}/reservations/{id}")
    public ResponseEntity<ReservationDto> getReservationByCarId(@PathVariable Long carid){
        return ResponseEntity.ok((ReservationDto) reservationService.getByCarId(carid));}

    @GetMapping ("/users/{userid}/reservations/{id}")
    public ResponseEntity<ReservationDto> getReservationByUserId(@PathVariable Long userid){
        return ResponseEntity.ok((ReservationDto) reservationService.getByUserid(userid));}

}