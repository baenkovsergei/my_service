package com.example.service.controller;

import com.example.service.entity.Category;
import com.example.service.service.CategoryService;

import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<Category> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(categoryService.getCategoryById(id));
    }

    //Заполнение для тестирования
    @PostMapping("/populate")
    public ResponseEntity<String> populate(@RequestParam Integer count) {
        categoryService.populateCategories(count);
        return ResponseEntity.ok().body("Добавлено категорий:" + count.toString());
    }
}
