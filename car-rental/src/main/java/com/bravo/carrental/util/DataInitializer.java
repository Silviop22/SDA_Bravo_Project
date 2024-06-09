package com.bravo.carrental.util;

import com.bravo.carrental.branch.model.Branch;
import com.bravo.carrental.branch.repository.BranchRepository;
import com.bravo.carrental.car.model.Car;
import com.bravo.carrental.car.repository.CarRepository;
import org.hibernate.mapping.List;
import org.hibernate.mapping.Map;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.math.BigDecimal;
import static com.bravo.carrental.car.model.brand.*;
import static com.bravo.carrental.car.model.model.*;
import static com.bravo.carrental.car.model.status.AVAILABLE;
import static java.awt.Color.*;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner carInitializeDatabase(CarRepository carRepository) {
        return args -> {
            carRepository.save(Car.builder()
                    .brand(BMW).model(Sport).price(BigDecimal.valueOf(100))
                    .serviceCost(BigDecimal.valueOf(1)).discount(BigDecimal.valueOf(0.01))
                    .year(2020).color(String.valueOf(black)).status(AVAILABLE).amount(3).build());
            carRepository.save(Car.builder()
                    .brand(Ferrari).model(Luxury).price(BigDecimal.valueOf(1000))
                    .serviceCost(BigDecimal.valueOf(10)).discount(BigDecimal.valueOf(0))
                    .year(2023).color(String.valueOf(red)).status(AVAILABLE).amount(1).build());
            carRepository.save(Car.builder()
                    .brand(MercedesBenz).model(SUV).price(BigDecimal.valueOf(70))
                    .serviceCost(BigDecimal.valueOf(1)).discount(BigDecimal.valueOf(0.1))
                    .year(2000).color(String.valueOf(yellow)).status(AVAILABLE).amount(10).build());};}

//    @Bean
//    CommandLineRunner branchInitializeDatabase(BranchRepository branchRepository) {
//        return args -> {
//            branchRepository.save(Branch.builder()
//                    .branchName("Branch 1").branchCity(TIRANA)
//                    .listOfEmployees("Employee 1", "Employee 2", "Employee 3")
//                    .listOfCars("BMW", "Ferrari", "MercedesBenz")).build());
//            branchRepository.save(Branch.builder()
//                    .branchName("Branch 2").branchCity(DURRES)
//                    .listOfEmployees("Employee 1", "Employee 2"))
//                    .listOfCars(("BMW", "Ferrari")).build());
//            branchRepository.save(Branch.builder()
//                    .branchName("Branch 3").branchCity(VLORE)
//                    .listOfEmployees((List) Arrays.asList("Employee 1", "Employee 2"))
//                    .listOfCars("BMW").build());};}
}