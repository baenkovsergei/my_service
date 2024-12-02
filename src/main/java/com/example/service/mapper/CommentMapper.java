package com.example.service.mapper;

import com.example.service.dto.CommentDTO;
import com.example.service.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentDTO toCommentDTO(Comment comment) {
        return new CommentDTO(comment.getCommentContent(),
                comment.getUserOne().getName(),
                comment.getCar().getModel());
    }

}
