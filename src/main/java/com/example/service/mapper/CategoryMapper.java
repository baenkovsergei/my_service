package com.example.service.mapper;

import com.example.service.dto.CategoryDTO;
import com.example.service.entity.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class CategoryMapper {


    public CategoryDTO categoryToDto(Category category) {
        return new CategoryDTO(category.getName());
    }

    public List<CategoryDTO> listCatToDto(List<Category> categories) {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (Category category : categories) {
            categoryDTOS.add(categoryToDto(category));
        }
        return categoryDTOS;
    }
}
