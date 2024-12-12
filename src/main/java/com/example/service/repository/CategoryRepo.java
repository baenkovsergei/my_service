package com.example.service.repository;

import com.example.service.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Integer> {


    @Query("SELECT c FROM Category c WHERE c.id IN :ids")
    List<Category> findCategoryByIds(@Param("ids") List<Integer> ids);

}
