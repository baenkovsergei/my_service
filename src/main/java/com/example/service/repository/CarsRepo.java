package com.example.service.repository;

import com.example.service.dto.CarsDTO2;
import com.example.service.entity.Cars;
import com.example.service.mapper.CommentMapper;
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

    /*
    @Query("""
    SELECT new com.example.service.dto.CarsDTO2(c.model, cat, comm)
    FROM Cars c
    LEFT JOIN c.categories cat
    LEFT JOIN c.comments comm
""")
    Page<CarsDTO2> findAllCarsPageDTO(Pageable pageable);
    */


    //Не уверен, что EntityGraph ваще работает
    //@EntityGraph(attributePaths = {"comments","categories"})
    @Query ("""
        select c from Cars c
        left join c.categories cat
        left join c.comments comm
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

