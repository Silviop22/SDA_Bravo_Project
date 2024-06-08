package com.bravo.carrental.car.repository;

import com.bravo.carrental.car.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Page<Car> findAll(Pageable pageable);
    Optional<Car> findByBrand(Enum brand);
    Optional<Car> findByModel(Enum model);
    Optional<Car> findByPrice(BigDecimal price);
    Optional<Car> findByYear(Integer year);
    Optional<Car> findByStatus(Enum status);
}