package com.example.service.repository;

import com.example.service.entity.Cars;
import com.example.service.projections.CarFull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarsRepo extends JpaRepository<Cars, Integer> {
//    @EntityGraph(value = "Cars.allDetails", type = EntityGraph.EntityGraphType.FETCH)
//
//    @Query("""
//        select distinct
//            c.model as model,
//            c.categories as categories,
//            c.comments as comments
//        from Cars c where c.id = :id
//    """)
//    @EntityGraph(value = "Cars.allDetails", type = EntityGraph.EntityGraphType.FETCH)
    @EntityGraph(attributePaths = {"categories","comments"})
    @Query("""
        select distinct cars from Cars cars
        WHERE cars.id = :id
    """)
    Optional<CarFull> findCarsById(Integer id);

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

