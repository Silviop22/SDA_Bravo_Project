package com.bravo.carrental.reservation.service;

import com.bravo.carrental.branch.service.ObjectPatcher;
import com.bravo.carrental.reservation.exception.DateException;
import com.bravo.carrental.reservation.mapper.ReservationMapper;
import com.bravo.carrental.reservation.model.Reservation;
import com.bravo.carrental.reservation.model.ReservationDto;
import com.bravo.carrental.reservation.model.ReservationValidation;
import com.bravo.carrental.reservation.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReservationService extends ObjectPatcher implements ReservationValidation {

    private final ReservationRepository repository;
    private final ReservationMapper mapper;

    public ReservationService(ReservationRepository repository, ReservationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;}

    @Transactional
    public ReservationDto getReservations() {
        return mapper.toDto((Reservation) repository.findAll());
    }

    @Transactional
    public ReservationDto getById(Long id) {
        return mapper.toDto(repository.getById(id));
    }

    @Transactional
    public List<ReservationDto> getByUserid(Long id) {
        return mapper.toDtoList(repository.findByUserId(id));
    }

    @Transactional
    public List<ReservationDto> getByCarId(Long id) {
        return mapper.toDtoList(repository.findByCarId(id));
    }

    @Transactional
    public void create(ReservationDto resDto) throws DateException {
        validateReservationCreation(resDto);
        if (isCarAvailable(resDto.getCar().getId(), resDto.getStart_date(), resDto.getEnd_date())) {
            Reservation reservation = mapper.toEntity(resDto);
            reservation = repository.save(reservation);
            resDto.setId(reservation.getId());} else {
            throw new DateException("Car is not available for the selected dates");
        }
    }

    @Transactional
    public void update(ReservationDto resDto, Long id) throws DateException {
        validateReservationCreation(resDto);
        Reservation existing = getExistingEntity((id));
        if (isCarAvailable(resDto.getCar().getId(), resDto.getStart_date(), resDto.getEnd_date(), id)) {
            Reservation updateCandidate = mapper.toEntity(resDto);
            ObjectPatcher.patchObject(updateCandidate, existing);
            repository.save(existing);} else {
            throw new DateException("Car is not available for the selected dates");}}

    private boolean isCarAvailable(Long carId, String startDateStr, String endDateStr) {
        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);
        List<Reservation> conflictingReservations = repository.findReservationsForCarBetweenDates(carId, startDate, endDate);
        return conflictingReservations.isEmpty();}

    private boolean isCarAvailable(Long carId, String startDateStr, String endDateStr, Long reservationId) {
        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);
        List<Reservation> conflictingReservations = repository.findReservationsForCarBetweenDates(carId, startDate, endDate);
        return conflictingReservations.stream().allMatch(reservation ->
                reservation.getId().equals(reservationId));}

    private Reservation getExistingEntity(Long id) {
        return repository.findById((id)).orElseThrow();
    }

    @Transactional
    public void deleteById(Long id) {
        getExistingEntity(id);
        repository.deleteById((id));}

    public boolean isValid(String dateStr) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {sdf.parse(dateStr);} catch (ParseException e) {
            return false;}
        return true;}

    @Override
    public void validateReservationCreation(ReservationDto reservationDtoDto) throws DateException {
        String startDateStr = reservationDtoDto.getStart_date();
        String endDateStr = reservationDtoDto.getEnd_date();

        if (!isValid(startDateStr) || !isValid(endDateStr)) {
            throw new DateException("Starting date or ending date are of the wrong formats");}

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {LocalDate startDate = LocalDate.parse(startDateStr, formatter);
            LocalDate endDate = LocalDate.parse(endDateStr, formatter);
            if (startDate.isAfter(endDate)) {
                throw new DateException("Start date should not be after end date");}}
        catch (DateException e) {System.out.println(e.getMessage());}}
}