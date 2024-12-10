package com.example.service.service;

import com.example.service.utils.RandomString;
import com.example.service.entity.Cars;
import com.example.service.repository.CarsRepo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CarsService {

    private final CarsRepo carsRepo;

    public List<Cars> getAllCars() {
        return carsRepo.findAllCars();
        //return carsRepo.findAll(); //Стандартный метод
    }

    public Page<Cars> getAllCarsPages(int page, int size) {
        Pageable carsPage = PageRequest.of(page,size);
        return carsRepo.getAllCarsPages(carsPage);
    }

    public Cars getCarByModel(String model) {
        Cars cars = carsRepo.findByModel(model);
        if (cars == null) {
            return null;
        }
        return cars;
    }

    public Cars getCarById(Integer id) {
        Optional<Cars> optionalCars = carsRepo.findCarById(id);
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

    //Заполнение данными для тестирования
    public void populateCars(Integer count){
        int start = carsRepo.findAll().size();
        for (Integer i = start+1; i < (count+start+1);i++) {
            Cars car = new Cars();
            car.setId(i);
            car.setModel(RandomString.getRandomWord(10));
            carsRepo.save(car);
        }
    }



}
