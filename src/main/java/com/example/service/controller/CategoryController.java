package com.example.service.controller;

import com.example.service.entity.Category;
import com.example.service.mapper.CategoryMapper;
import com.example.service.service.CategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category/")
@RequiredArgsConstructor

public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<Category>> getAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Category> getById2(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(categoryService.getCategoryById(id));
    }
}
