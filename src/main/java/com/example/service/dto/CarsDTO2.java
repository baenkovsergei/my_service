package com.example.service.dto;
import com.example.service.entity.Category;
import com.example.service.entity.Comment;
import lombok.Data;

//TEMPORARY
import java.util.List;

@Data
public class CarsDTO2 {

    private String model;
    private List<Category> categories;
    private List<Comment> comment;

    public CarsDTO2(String model, List<Category> categories, List<Comment> comment) {
        this.model = model;
        this.categories = categories;
        this.comment = comment;
    }

}
