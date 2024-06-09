package com.bravo.carrental.branch.model;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.mapping.Map;

import java.util.List;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class BranchDto {
    private BranchDto branchDto;
    @Id
    private Long id;
    @NotBlank(message = "Branch name is mandatory", groups = BranchCreationValidation.class)
    private String branchName;
    @NotBlank(message = "Branch city is mandatory", groups = BranchCreationValidation.class)
    private Enum branchCity;
    @NotBlank(message = "List of employees is mandatory", groups = BranchCreationValidation.class)
    private List listOfEmployees;
    @NotBlank(message = "List of cars is mandatory", groups = BranchCreationValidation.class)
    private List listOfCars;

    public BranchDto (){};
}
