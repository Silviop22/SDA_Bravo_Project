package com.bravo.carrental.reporting.model;

import com.bravo.carrental.car.model.CarDto;
import lombok.Data;

@Data
public class YearlyReportEntry {
    private String month;
    private Long totalReservations;
    private CarDto mostPopularCar;
    private CarDto leastPopularCar;
    private Long totalIncome;
}
