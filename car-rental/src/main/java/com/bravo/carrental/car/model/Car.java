package com.bravo.carrental.car.model;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Builder
@Data
@Entity
@Table(name = "cars")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "brand", nullable = false)
    private Enum brand;
    @Column(name = "model", nullable = false)
    private Enum model;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Column(name = "service_cost", nullable = false)
    private BigDecimal serviceCost;
    @Column(name = "discount", nullable = false)
    private BigDecimal discount;
    @Column(name = "year", nullable = false)
    private Integer year;
    @Column(name = "color", nullable = false)
    private String color;
    @Column(name = "status", nullable = false)
    private Enum status;
    @Column(name = "amount", nullable = false)
    private Integer amount;

}