package com.example.service.controller;

import com.example.service.dto.CategoryDTO;
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
    @Autowired
    private final CategoryMapper categoryMapper;

    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getAll() {
        return ResponseEntity.ok(categoryMapper.listCatToDto(categoryService.findAll()));
    }

    @GetMapping ("/{id}")
    public ResponseEntity<CategoryDTO> getById2(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(categoryMapper.categoryToDto(categoryService.getCategoryById(id)));
    }
}
