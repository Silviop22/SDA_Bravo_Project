package com.bravo.carrental.car.service;

import com.bravo.carrental.branch.service.ObjectPatcher;
import com.bravo.carrental.car.mapper.CarMapper;
import com.bravo.carrental.car.model.Car;
import com.bravo.carrental.car.model.CarDto;
import com.bravo.carrental.car.repository.CarRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CarService extends ObjectPatcher {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public CarService(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;}

    @Transactional
    public CarDto getById(Long id) {
        return carMapper.toDto(carRepository.findById((id))
                .orElseThrow());}

    @Transactional
    public Page<CarDto> getList(int page, int size) {
        Pageable pageble = PageRequest.of(page, size);
        return carRepository.findAll(pageble)
                .map(carMapper::toDto);}

    @Transactional
    public CarDto create(CarDto carDto) {
        String brand = carDto.getBrand();
        String model = carDto.getModel();
        if (carRepository.findByBrand(brand).isPresent()
                && carRepository.findByModel(model).isPresent()) {
            throw new IllegalArgumentException();}
        Car car = carMapper.toEntity(carDto);
        car = carRepository.save(car);
        carDto.setId(car.getId());
        return carDto;}

    @Transactional
    public void update(CarDto itemDto, Long id) {
        Car existing = getExistingEntity((id));
        Car updateCandidate = carMapper.toEntity(itemDto);
        ObjectPatcher.patchObject(updateCandidate, existing);
        carRepository.save(existing);}

    @Transactional
    public void deleteById(Long id) {
        getExistingEntity(id);
        carRepository.deleteById((id));}

    private Car getExistingEntity(Long id) {
        return carRepository.findById((id))
                .orElseThrow();}
}