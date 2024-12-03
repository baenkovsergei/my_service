package com.example.service.repository;

import com.example.service.entity.Cars;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarsRepo extends JpaRepository<Cars, Integer> {

}
