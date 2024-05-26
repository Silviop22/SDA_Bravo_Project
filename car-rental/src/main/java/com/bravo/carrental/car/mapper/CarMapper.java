package com.bravo.carrental.car.mapper;
import com.bravo.carrental.car.model.Car;
import com.bravo.carrental.car.model.CarDto;
import com.bravo.carrental.util.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CarMapper implements ModelMapper<CarDto, Car> {

    @Override
    public Car toEntity(CarDto carDto) {
        if (carDto == null){return null;}
        return Car.builder()
                .id(carDto.getId())
                .brand(carDto.getBrand())
                .model(carDto.getModel())
                .price(carDto.getPrice())
                .serviceCost(carDto.getServiceCost())
                .discount(carDto.getDiscount())
                .year(carDto.getYear())
                .color(carDto.getColor())
                .status(carDto.getStatus())
                .amount(carDto.getAmount())
                .build();}

    @Override
    public CarDto toDto(Car car) {
        if (car == null){return null;}
        return CarDto.builder().id(car.getId())
                .brand(car.getBrand())
                .model(car.getModel())
                .price(car.getPrice())
                .serviceCost(car.getServiceCost())
                .discount(car.getDiscount())
                .year(car.getYear())
                .color(car.getColor())
                .status(car.getStatus())
                .amount(car.getAmount())
                .build();}
}