package com.example.service.dto;
import com.example.service.entity.Category;
import lombok.Data;


import java.util.List;
import java.util.Set;

@Data
public class CarsDTO {
    
    private String model;
    private Set<Category> categories;
    private List<CommentDTO> comment;

    public CarsDTO(String model, Set<Category> categories, List<CommentDTO> comment) {
        this.model = model;
        this.categories = categories;
        this.comment = comment;
    }

}
