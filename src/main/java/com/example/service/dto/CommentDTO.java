package com.example.service.dto;import com.example.service.dto.CarsDTO;

import lombok.Data;

@Data

public class CommentDTO {
    private String content;
    private String author;
    private String car;

    public CommentDTO(String content, String author, String car) {
       this.content = content;
       this.author = author;
       this.car = car;
    }
}
