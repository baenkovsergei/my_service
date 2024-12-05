package com.example.service.repository;

import com.example.service.entity.Comment;
import com.example.service.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsersRepo extends JpaRepository<Users, Integer> {

    @Query("""
        select u from Users u
        left join u.comments
    """)
    List<Users> findAllUsers();

    //left join u.comments
    @Query("""
        select u from Users u
        where u.name = :username
    """)
    Users findUsersByUsername(@Param("username") String username);

}
