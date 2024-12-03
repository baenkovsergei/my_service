package com.example.service.service;

import com.example.service.entity.Cars;
import com.example.service.repository.CarsRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CarsService {

    private final CarsRepo carsRepo;

    public List<Cars> getAllCars() {
        return carsRepo.findAll();
    }

    public Cars getCarById(Integer id) {
        Optional<Cars> optionalCars = carsRepo.findById(id);
        if (optionalCars.isPresent()) {
            return optionalCars.get();
        }
        return null;
    }

    public Cars saveCars(Cars car) {
        Cars savedCar = carsRepo.save(car);
        return savedCar;
    }

    public Cars updateCars (Cars car) {
        Optional<Cars> optionalCars = carsRepo.findById(car.getId());
        Cars updatedCar = carsRepo.save(car);
        return updatedCar;
    }

    public void deleteById(Integer id) {
        carsRepo.deleteById(id);
    }

}
