package com.example.service.mapper;

import com.example.service.dto.CategoryDTO;
import com.example.service.entity.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper {

    public CategoryDTO ToDto(Category category) {
        return new CategoryDTO(category.getName());
    }

    public List<CategoryDTO> listCatToDto(List<Category> categories) {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (Category category : categories) {
            categoryDTOS.add(ToDto(category));
        }
        return categoryDTOS;
    }

    public Category DTOToCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        return category;
    }
}
