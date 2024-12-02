package com.example.service.service;

import com.example.service.dto.CategoryDTO;
import com.example.service.entity.Category;
import com.example.service.mapper.CategoryMapper;
import com.example.service.repository.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CategoryService {

    private final CategoryRepo categoryRepo;
    private final CategoryMapper categoryMapper = new CategoryMapper();


    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    public CategoryDTO getCatById(Integer id) {
        Optional<CategoryDTO> optionalCategory = categoryRepo.findById(id).map(categoryMapper::categoryToDto);
        if (optionalCategory.isPresent()) {
            return optionalCategory.get();
        }
        return null;
    }

}
