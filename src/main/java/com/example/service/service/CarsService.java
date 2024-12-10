package com.example.service.service;

import com.example.service.entity.Category;
import com.example.service.repository.CategoryRepo;
import com.example.service.utils.RandomString;
import com.example.service.entity.Cars;
import com.example.service.repository.CarsRepo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor

public class CarsService {

    private final CarsRepo carsRepo;
    private final CategoryRepo categoryRepo;
    private final CategoryService categoryService;

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

    public Cars updateCategory (Integer carId, Integer categoryId) {
        Optional<Cars> optionalCars = carsRepo.findCarById(carId);
        if (!optionalCars.isPresent()) {
            return null;
        }

        Optional<Category> optionalCategory = categoryRepo.findById(categoryId);
        if(!optionalCategory.isPresent()) {
            return null;
        }

        optionalCars.get().getCategories().add(optionalCategory.get());

        return optionalCars.get();
    }

    public Cars updateFewCategory (Integer carId, List<Integer> categoryIds) {
        Optional<Cars> optionalCars = carsRepo.findCarById(carId);
        if (!optionalCars.isPresent()) {
            return null;
        }
        List<Category> categoryList = categoryService.getFewCatById(categoryIds);
        if (!categoryList.isEmpty()) {
            return null;
        }
        optionalCars.get().getCategories().addAll(categoryList);
        return optionalCars.get();
    }

}
