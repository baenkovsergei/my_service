package com.example.service.controller;

import com.example.service.entity.Cars;
import com.example.service.service.CarsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/cars/")
@RestController
@RequiredArgsConstructor
public class CarsController {

    private final CarsService carsService;

    @GetMapping("/")
    public ResponseEntity<List<Cars>> getAllCars(){
        return ResponseEntity.ok().body(carsService.getAllCars());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cars> getCarById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(carsService.getCarById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Cars> saveCar(@RequestBody Cars car) {
        return ResponseEntity.ok().body(carsService.saveCars(car));
    }

    @PutMapping("/")
    public ResponseEntity<Cars> updateCar(@RequestBody Cars car) {
        return ResponseEntity.ok().body(carsService.updateCars(car));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cars> deleteCar(@PathVariable Integer id) {
        carsService.deleteById(id);
        return ResponseEntity.ok().body(null);
    }

}
