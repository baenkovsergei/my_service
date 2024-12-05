package com.example.service.dto;
import com.example.service.entity.Category;
import lombok.Data;


import java.util.List;

@Data
public class CarsDTO {
    
    private String model;
    private List<Category> categories;
    private List<CommentDTO> comment;

    public CarsDTO(String model, List<Category> categories, List<CommentDTO> comment) {
        this.model = model;
        this.categories = categories;
        this.comment = comment;
    }

}
