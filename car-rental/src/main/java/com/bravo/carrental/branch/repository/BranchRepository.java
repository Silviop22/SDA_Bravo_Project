package com.bravo.carrental.branch.repository;

import com.bravo.carrental.branch.model.Branch;
import com.bravo.carrental.car.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
    Optional<Object> findByBranchName(String branchName);
    Optional<Branch> findByCity(String branchCity);
    Page<Branch> findAll(Pageable pageable);
}