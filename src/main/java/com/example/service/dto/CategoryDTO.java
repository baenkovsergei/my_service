package com.example.service.dto;

import lombok.Data;

@Data
public class CategoryDTO {
    private String name;

    public CategoryDTO(String category) {
        this.name = category;
    }
}
