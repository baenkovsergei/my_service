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

    @Query ("""
        select distinct c from Cars c
        left join fetch c.categories cat
        left join c.comments comm
        left join comm.userOne
    """)
    List<Cars> findAllCars();

    @Query ("""
        select distinct c from Cars c
        left join fetch c.categories cat
        left join c.comments comm
    """)
    Page<Cars> getAllCarsPages(Pageable pageable);

    @Query("""
        select distinct cars from Cars cars
        left join fetch cars.categories cat
        left join cars.comments comm
        where cars.model = :model
    """)
    Cars findByModel(String model);

    //@EntityGraph(attributePaths = {"categories"})
    @Query("""
    select distinct c from Cars c
    left join fetch c.categories cat
    left join c.comments comm
    where c.id = :carId
    """)
    Optional<Cars> findCarById(Integer carId);

    //Multiple bags fetch exception
//    @EntityGraph(attributePaths = {"comments", "categories"})
//    @Query("""
//    select c from Cars c
//    join c.categories cat
//    join c.comments comm
//    where c.id = :carId
//    """)
    @EntityGraph(value = "Cars.allDetails", type = EntityGraph.EntityGraphType.FETCH)
    @Query("""
    select c from Cars c where c.id = :carId
    """)
    Optional<Cars> findCarById2(Integer carId);

}

