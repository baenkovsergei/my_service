package com.example.service.dto;
import lombok.Data;

import java.util.List;

@Data
public class CarsDTO {
    
    private String model;
    private List<CategoryDTO> categories;
    private List<CommentDTO> comment;

    public CarsDTO(String model, List<CategoryDTO> categories, List<CommentDTO> comment) {
        this.model = model;
        this.categories = categories;
        this.comment = comment;
    }

}
