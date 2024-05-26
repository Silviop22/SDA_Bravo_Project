package com.bravo.carrental.car.model;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class CarDto {
    private CarDto carDto;
    @Id
    private Long id;
    @NotBlank(message = "Brand is mandatory", groups = CarCreationValidation.class)
    private String brand;
    @NotBlank(message = "Model is mandatory", groups = CarCreationValidation.class)
    private String model;
    @NotBlank(message = "Price is mandatory", groups = CarCreationValidation.class)
    private BigDecimal price;
    @NotBlank(message = "Service cost is mandatory", groups = CarCreationValidation.class)
    private BigDecimal serviceCost;
    @NotBlank(message = "Discount is mandatory", groups = CarCreationValidation.class)
    private BigDecimal discount;
    @NotBlank(message = "Year is mandatory", groups = CarCreationValidation.class)
    private Integer year;
    @NotBlank(message = "Color is mandatory", groups = CarCreationValidation.class)
    private String color;
    @NotBlank(message = "Status is mandatory", groups = CarCreationValidation.class)
    private Enum status;
    @NotBlank(message = "Amount is mandatory", groups = CarCreationValidation.class)
    private Integer amount;

    public CarDto (){};
}