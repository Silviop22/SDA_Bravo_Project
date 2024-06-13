package com.bravo.carrental.reservation.mapper;

import com.bravo.carrental.reservation.model.Reservation;
import com.bravo.carrental.reservation.model.ReservationDto;
import com.bravo.carrental.util.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReservationMapper implements ModelMapper<ReservationDto, Reservation> {

    @Override
    public Reservation toEntity(ReservationDto dto) {
        if (dto == null) {
            return null;
        }
        return Reservation.builder()
                .id(dto.getId())
                .status(dto.getStatus())
                .start_date(dto.getStart_date())
                .end_date(dto.getEnd_date())
                .user(dto.getUser())
                .car(dto.getCar())
                .build();
    }

    @Override
    public ReservationDto toDto(Reservation entity) {
        if (entity == null) {
            return null;
        }
        return ReservationDto.builder()
                .id(entity.getId())
                .status(entity.getStatus())
                .start_date(entity.getStart_date())
                .end_date(entity.getEnd_date())
                .user(entity.getUser())
                .car(entity.getCar())
                .build();
    }

    public List<ReservationDto> toDtoList(List<Reservation> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


}
