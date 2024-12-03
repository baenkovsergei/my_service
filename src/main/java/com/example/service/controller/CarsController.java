package com.example.service.controller;

import com.example.service.entity.Cars;
import com.example.service.dto.CarsDTO;
import com.example.service.mapper.CarsMapper;
import com.example.service.service.CarsService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cars/")
@RequiredArgsConstructor
public class CarsController {

    private final CarsService carsService;
    @Autowired
    private final CarsMapper carsMapper;

    @GetMapping("/")
    public ResponseEntity<List<CarsDTO>> getAllCars(){
        return ResponseEntity.ok().body(carsMapper.toCarsDTO(carsService.getAllCars()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarsDTO> getCarById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(carsMapper.toCarsDTO(carsService.getCarById(id)));
    }

    @PostMapping("/")
    public ResponseEntity<CarsDTO> saveCar(@RequestBody Cars car) {
        return ResponseEntity.ok().body(carsMapper.toCarsDTO(carsService.saveCars(car)));
    }

    @PutMapping("/")
    public ResponseEntity<CarsDTO> updateCar(@RequestBody Cars car) {
        return ResponseEntity.ok().body(carsMapper.toCarsDTO(carsService.updateCars(car)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cars> deleteCar(@PathVariable Integer id) {
        carsService.deleteById(id);
        return ResponseEntity.ok().body(null);
    }

}
