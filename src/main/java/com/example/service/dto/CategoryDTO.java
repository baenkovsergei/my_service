package com.example.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDTO {
    private String name;

    public CategoryDTO(String category) {
        this.name = category;
    }
}
