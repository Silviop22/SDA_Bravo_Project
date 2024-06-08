package com.bravo.carrental.reporting.service;

import com.bravo.carrental.car.mapper.CarMapper;
import com.bravo.carrental.car.model.Car;
import com.bravo.carrental.car.model.CarDto;
import com.bravo.carrental.reporting.model.YearlyReportEntry;
import com.bravo.carrental.reservation.Reservation;
import com.bravo.carrental.reservation.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportingService {
    private final ReservationRepository reservationRepository;
    private final CarMapper carMapper;

    private List<YearlyReportEntry> getYearlyReport() {
        List<YearlyReportEntry> reportEntries = new ArrayList<>();
        Map<Month, List<Reservation>> reservationsByMonth = getReservationsGroupedByMonth();


        for (Map.Entry<Month, List<Reservation>> entry : reservationsByMonth.entrySet() ) {
            YearlyReportEntry reportEntry = new YearlyReportEntry();
            Month month = entry.getKey();
            reportEntry.setMonth(month.toString());

            List<Reservation> reservationList = entry.getValue();
            reportEntry.setTotalReservations((long) reservationList.size());

            CarDto carDtoMostUsed = reservationRepository.findMostUsedCarInMonth(month.getValue())
                    .map(carMapper::toDto)
                    .orElse(null);

            CarDto carDtoLeastUsed = reservationRepository.findLeaseUsedCarInMonth(month.getValue())
                    .map(carMapper::toDto)
                    .orElse(null);

            reportEntry.setMostPopularCar(carDtoMostUsed);
            reportEntry.setLeastPopularCar(carDtoLeastUsed);

            BigDecimal totalIncome = reservationRepository.findTotalIncomeByMonth(month.getValue());

            reportEntry.setTotalIncome(totalIncome.longValue());
            reportEntries.add(reportEntry);
        }

        return reportEntries;
    }

    public Map<Month, List<Reservation>> getReservationsGroupedByMonth() {
        List<Object[]> groupedReservations = reservationRepository.findReservationsGroupedByMonth();
        return groupedReservations.stream()
                .collect(Collectors.toMap(
                        objects -> Month.of((Integer) objects[0]),
                        objects -> (List<Reservation>) objects[1]
                ));
    }
}
