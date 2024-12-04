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
    """)
    List<Cars> findAllCars();

    //Не уверен, что EntityGraph ваще работает
    //@EntityGraph(attributePaths = {"comments","categories"})
    @Query ("""
        select c from Cars c
        left join fetch c.categories cat
        left join fetch c.comments comm
    """)
    Page<Cars> giveAllCars(Pageable pageable);

    @EntityGraph(attributePaths = {"categories"})
    @Query("""
    select distinct c from Cars c
    left join c.categories cat
    left join c.comments comm
    where c.id = :car_id
    """)
    Optional<Cars> findCarById(int car_id);

}

