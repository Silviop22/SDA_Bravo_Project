package com.bravo.carrental.car.model;
import com.bravo.carrental.reservation.Reservation;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@Entity
@Table()
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "brand", nullable = false)
    private String brand;
    @Column(name = "model", nullable = false)
    private String model;
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

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reservation> reservationList;

}