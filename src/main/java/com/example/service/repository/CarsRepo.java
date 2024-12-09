package com.example.service.repository;

import com.example.service.entity.Cars;

import com.example.service.entity.Category;
import com.example.service.entity.Comment;
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

    @EntityGraph(attributePaths = {"comments.userOne", "categories"})
    @Query("""
    select distinct c from Cars c
    left join c.categories cat
    left join c.comments comm
    where c.id = :carId
    """)
    Optional<Cars> findCarById2(Integer carId);

}

