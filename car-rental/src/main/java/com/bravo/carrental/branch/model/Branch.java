package com.bravo.carrental.branch.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.mapping.List;

@Builder
@Data
@Entity
@Table()
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "branch_name", nullable = false)
    private String branchName;
    @Column(name = "branch_city", nullable = false)
    private String branchCity;
    @Column(name = "list_of_employees", nullable = false)
    private List listOfEmployees;
    @Column(name = "list_of_cars", nullable = false)
    private List listOfCars;

}