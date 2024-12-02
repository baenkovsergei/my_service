package com.example.service.dto;
import com.example.service.dto.CommentDTO;
import com.example.service.entity.Category;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class CategoryDTO {
    private String name;

    public CategoryDTO(String category) {
        this.name = category;
    }
}
