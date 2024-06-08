package com.bravo.carrental.car.controller;
import com.bravo.carrental.car.model.CarDto;
import com.bravo.carrental.car.service.CarService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cars")
 public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {this.carService = carService;}

        @GetMapping("/{id}")
        public ResponseEntity<CarDto> getItemById(@PathVariable Long id) {
            return ResponseEntity.ok(carService.getById(id));}

        @GetMapping
        public ResponseEntity<List<CarDto>> getItems(@RequestParam("page") int page,
                                                     @RequestParam("size") int size) {
            Page<CarDto> result = carService.getList(page, size);
            return ResponseEntity.ok(result.getContent());}

        @PostMapping
        public ResponseEntity<Void> createCar(@RequestBody CarDto carDto) {
            CarDto result = carService.create(carDto);
            return ResponseEntity.created(URI.create("/cars/" + result.getId()))
                    .build();}

        @PatchMapping("/{id}")
        public ResponseEntity<Void> updateCar(@RequestBody CarDto carDto,
                                              @PathVariable Long id) {
            carService.update(carDto, id);
            return ResponseEntity.noContent().build();}

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
            carService.deleteById(id);
            return ResponseEntity.noContent().build();}
    }