package com.example.service.controller;

import com.example.service.entity.Cars;
import com.example.service.dto.CarsDTO;
import com.example.service.mapper.CarsMapper;
import com.example.service.service.CarsService;

import org.springframework.data.domain.Page;
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
        List<Cars> allCars = carsService.getAllCars();
        List<CarsDTO> carsDTO = carsMapper.toCarsDTO(allCars);
        return ResponseEntity.ok().body(carsDTO);
    }
    //testing
    @GetMapping("/pages")
    public ResponseEntity<Page<Cars>> getAllCarsPages(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "5") int size) {
        Page<Cars> cars = carsService.getAllCarsPages(page, size);
        return ResponseEntity.ok().body(cars);
    }

    @GetMapping("/pagesV2")
    public ResponseEntity<List<CarsDTO>> getAllCarsPagesV2(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "5") int size) {
        Page<Cars> carsPage = carsService.getAllCarsPages(page,size);
        List<Cars> carsList = carsPage.getContent();
        List<CarsDTO> cars = carsMapper.toCarsDTO(carsList);
        return ResponseEntity.ok().body(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarsDTO> getCarById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(carsMapper.toCarsDTO(carsService.getCarById(id))); //TESTING(НАДО ВЕРНУТЬ)
    }

    @GetMapping("/search")
    public ResponseEntity<CarsDTO> searchCars(@RequestParam("model") String model) {
        return ResponseEntity.ok().body(carsMapper.toCarsDTO(carsService.getCarByModel(model)));
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

    //Заполнение для тестирования
    @PostMapping("/populate")
    public ResponseEntity<String> populateCars(@RequestParam Integer count){
        carsService.populateCars(count);
        return ResponseEntity.ok().body("Добавлено машин:" + count);
    }

}
