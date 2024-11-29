package com.example.service.repository;

import com.example.service.entity.Cars;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarsRepo extends JpaRepository<Cars, Integer> {

}
