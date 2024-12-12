package com.example.service.controller;

import com.example.service.dto.CategoryDTO;
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
    @Autowired
    private final CategoryMapper categoryMapper;

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping ("/{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(categoryMapper.ToDto(categoryService.getCategoryById(id)));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Category>> getFew(@RequestParam List<Integer> ids) {
        return ResponseEntity.ok().body(categoryService.getFewCatById(ids));
    }

//    @PostMapping("/add")
//    public ResponseEntity<Category> add(@RequestParam String name) {
//        return ResponseEntity.ok().body(categoryService.save(name));
//    }

//    @PostMapping("/add")
//    public ResponseEntity<Category> add2(@RequestBody CategoryDTO category) {
//        return ResponseEntity.ok().body(categoryService.save2(categoryMapper.DTOToCategory(category)));
//    }

    @PostMapping("/add")
    public ResponseEntity<Category> add2(@RequestBody CategoryDTO category) {

        Category category1 = categoryMapper.DTOToCategory(category);

        return ResponseEntity.ok().body(categoryService.save2(category1));
    }

    //Заполнение для тестирования
    @PostMapping("/populate")
    public ResponseEntity<String> populate(@RequestParam Integer count) {
        categoryService.populateCategories(count);
        return ResponseEntity.ok().body("Добавлено категорий:" + count.toString());
    }


}
