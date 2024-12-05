package com.example.service.controller;

import com.example.service.entity.Cars;
import com.example.service.dto.CarsDTO;
import com.example.service.mapper.CarsMapper;
import com.example.service.service.CarsService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    @GetMapping("/pages")
    public ResponseEntity<Page<Cars>> getAllCarsPages(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "5") int size) {
        Page<Cars> cars = carsService.getAllCarsPages(page, size);
        return ResponseEntity.ok().body(cars);
    }

    //Работает, но смысла нет, много запросов. Для того чтобы работало, необходимо не вызывать данные классов после пагинации
    //поскольку из-за этого в процессе выполнения маппинга он берёт поля зависимых сущностей, чтобы выполнить их маппинг
    //Видимо в версии V2 это работает поскольку данные на самом деле не нужны до момента выдачи
    @GetMapping("/pagesV2")
    public ResponseEntity<List<CarsDTO>> getAllCarsPagesV3(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "5") int size) {
        Page<Cars> carsPage = carsService.getAllCarsPages(page,size);
        List<Cars> carsList = carsPage.getContent();
        //List<CarsDTO> cars = carsPage.stream().map(carsMapper::toCarsDTO).collect(Collectors.toList());
        List<CarsDTO> cars = carsMapper.toCarsDTO(carsList);
        return ResponseEntity.ok().body(cars);
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
