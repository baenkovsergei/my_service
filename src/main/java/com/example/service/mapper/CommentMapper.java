package com.example.service.mapper;

import com.example.service.dto.CommentDTO;
import com.example.service.entity.Comment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentMapper {

    public CommentDTO toCommentDTO(Comment comment) {
        return new CommentDTO(comment.getCommentContent(),
                comment.getUserOne().getName(),
                comment.getCar().getModel());
    }

    public List<CommentDTO> toCommentDTOList(List<Comment> comments) {
        List<CommentDTO> commentDTOS = comments.stream().map(this::toCommentDTO).distinct().toList();
        return commentDTOS;
    }

    public List<CommentDTO> toCommentDTO(List<Comment> comments) {
        List<CommentDTO> commentsDTO = new ArrayList<>();
        for (Comment comment : comments) {
            commentsDTO.add(toCommentDTO(comment));
        }
        return commentsDTO;
    }

}
