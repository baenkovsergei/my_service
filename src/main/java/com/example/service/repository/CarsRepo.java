package com.example.service.repository;

import com.example.service.entity.Cars;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarsRepo extends JpaRepository<Cars, Integer> {

    @EntityGraph(value = "Cars.allDetails", type = EntityGraph.EntityGraphType.FETCH)
    @Query ("""
        select distinct c from Cars c
    """)
    List<Cars> findAllCars();

    @EntityGraph(value = "Cars.allDetails", type = EntityGraph.EntityGraphType.FETCH)
    @Query ("""
        select c from Cars c
        left join c.categories cat
        left join c.comments comm
    """)
    Page<Cars> getAllCarsPages(Pageable pageable);

    @EntityGraph(value = "Cars.allDetails", type = EntityGraph.EntityGraphType.FETCH)
    @Query("""
        select distinct cars from Cars cars
        where cars.model = :model
    """)
    Cars findByModel(String model);

    @EntityGraph(value = "Cars.allDetails", type = EntityGraph.EntityGraphType.FETCH)
    @Query("""
    select c from Cars c where c.id = :carId
    """)
    Optional<Cars> findCarById(Integer carId);

}

