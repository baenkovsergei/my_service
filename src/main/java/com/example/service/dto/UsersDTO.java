package com.example.service.dto;

import com.example.service.entity.Comment;
import lombok.Data;
import java.util.List;

@Data
public class UsersDTO {

    private String name;
    private List<CommentDTO> comment;

    public UsersDTO(String name, List<CommentDTO> comment) {
        this.name = name;
        this.comment = comment;
    }
}
