package com.example.service.service;

import com.example.service.entity.Category;
import com.example.service.mapper.CategoryMapper;
import com.example.service.repository.CategoryRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CategoryService {

    private final CategoryRepo categoryRepo;
    @Autowired
    private final CategoryMapper categoryMapper;

    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    public Category getCategoryById(Integer id) {
        Optional<Category> category = categoryRepo.findById(id);
        if (category.isPresent()) {
            return category.get();
        }
        return null;
    }

    //Не работает
    public Category save(String name) {
        Category category = new Category();
        category.setName(name);
        return categoryRepo.save(category);
    }

    public List<Category> getFewCatById(List<Integer> ids) {
        return categoryRepo.findCategoryByIds(ids);
    }

    //Заполнение данными для тестирования
    public void populateCategories(Integer count){
        int start = categoryRepo.findAll().size();
        for (Integer i = start+1; i < (count+start+1);i++) {
            Category category = new Category();
            category.setId(i);
            category.setName("Категория-" + i.toString());
            categoryRepo.save(category);
        }
    }

}
