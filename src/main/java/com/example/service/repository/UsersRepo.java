package com.example.service.repository;

import com.example.service.entity.Users;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsersRepo extends JpaRepository<Users, Integer> {

    @EntityGraph(value = "Users.allComments", type = EntityGraph.EntityGraphType.FETCH)
    @Query("""
        select u from Users u
    """)
    List<Users> findAllUsers();

    @EntityGraph(value = "Users.allComments", type = EntityGraph.EntityGraphType.FETCH)
    @Query("""
        select u from Users u
        where u.name = :username
    """)
    Users findUsersByUsername(@Param("username") String username);

    @EntityGraph(value = "Users.allComments", type = EntityGraph.EntityGraphType.FETCH)
    @Query("""
        select u from Users u
        where u.id = :id
    """)
    Optional<Users> findUsersById(Integer id);
}
