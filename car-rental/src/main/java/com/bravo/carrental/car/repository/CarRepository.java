package com.bravo.carrental.car.repository;

import com.bravo.carrental.car.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Page<Car> findAll(Pageable pageable);
    Optional<Car> findByBrand(String brand);
    Optional<Car> findByModel(String model);
}