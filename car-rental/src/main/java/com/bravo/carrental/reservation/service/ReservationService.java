package com.bravo.carrental.reservation.service;

import com.bravo.carrental.branch.service.ObjectPatcher;
import com.bravo.carrental.reservation.exception.DateException;
import com.bravo.carrental.reservation.mapper.ReservationMapper;
import com.bravo.carrental.reservation.model.Reservation;
import com.bravo.carrental.reservation.model.ReservationDto;
import com.bravo.carrental.reservation.model.ReservationValidation;
import com.bravo.carrental.reservation.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService extends ObjectPatcher implements ReservationValidation {

    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);
    private final ReservationRepository repository;
    private final ReservationMapper mapper;

    @Transactional
    public List<ReservationDto> getReservations() {
        return repository.findAll().stream()
                .map(mapper::toDto).toList();
    }

    @Transactional
    public ReservationDto getById(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> {
            logger.error("Reservation not found for ID: {}", id);
            return new RuntimeException("Reservation not found");
        }));
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
        if (isCarAvailable(resDto.getCar().getId(), resDto.getStart_date(),
                resDto.getEnd_date())) {
            Reservation reservation = mapper.toEntity(resDto);
            reservation = repository.save(reservation);
            resDto.setId(reservation.getId());
        } else {
            logger.error("Car is not available for the selected dates for car ID: {}", resDto.getCar().getId());
            throw new DateException("Car is not available for the selected dates");
        }
    }

    @Transactional
    public void update(ReservationDto resDto, Long id) throws DateException {
        validateReservationCreation(resDto);
        Reservation existing = getExistingEntity(id);
        if (isCarAvailable(resDto.getCar().getId(), resDto.getStart_date(), resDto.getEnd_date(), id)) {
            Reservation updateCandidate = mapper.toEntity(resDto);
            ObjectPatcher.patchObject(updateCandidate, existing);
            repository.save(existing);
        } else {
            logger.error("Car is not available for the selected dates for car ID: {}", resDto.getCar().getId());
            throw new DateException("Car is not available for the selected dates");
        }
    }

    private boolean isCarAvailable(Long carId, String startDateStr, String endDateStr) {
        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);
        List<Reservation> conflictingReservations = repository.findReservationsForCarBetweenDates(carId, startDate, endDate);
        return conflictingReservations.isEmpty();
    }

    private boolean isCarAvailable(Long carId, String startDateStr, String endDateStr, Long reservationId) {
        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);
        List<Reservation> conflictingReservations = repository.findReservationsForCarBetweenDates(carId, startDate, endDate);
        return conflictingReservations.stream().allMatch(reservation ->
                reservation.getId().equals(reservationId));
    }

    private Reservation getExistingEntity(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            logger.error("Reservation not found for ID: {}", id);
            return new RuntimeException("Reservation not found");
        });
    }

    @Transactional
    public void deleteById(Long id) {
        getExistingEntity(id);
        repository.deleteById(id);
    }

    public boolean isValid(String dateStr) {
        try {
            LocalDate.parse(dateStr);
            return true;
        } catch (Exception e) {
            logger.error("Invalid date format: {}", dateStr, e);
            return false;
        }
    }

    @Override
    public void validateReservationCreation(ReservationDto reservationDtoDto) throws DateException {

        String startDateStr = reservationDtoDto.getStart_date();
        String endDateStr = reservationDtoDto.getEnd_date();

        if (!isValid(startDateStr) || !isValid(endDateStr)) {
            logger.error("Invalid start date {} or end date {}", startDateStr, endDateStr);
            throw new DateException("Invalid start date or end date");
        }

        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);

        if (!startDate.isBefore(endDate)) {
            logger.error("Start date {} is not before end date {}", startDate, endDate);
            throw new DateException("Start date must be before end date");
        }
    }
}
