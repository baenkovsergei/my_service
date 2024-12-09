package com.example.service.repository;

import com.example.service.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

    @Query("""
        select c from Comment c
        join fetch c.car car
        join fetch c.userOne user
        where user.name = :name AND car.model = :model
    """)
    List<Comment> findCommByUsrCar(String name, String model);

    @Query("""
        select c from Comment c
        left join fetch c.userOne user
        left join fetch c.car car
        where c.userOne.id = :userId and car.id = :carId
    """)
    List<Comment> findCommByUsrCar(Integer userId, Integer carId);
}
