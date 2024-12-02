package com.example.service.service;
import com.example.service.dto.CommentDTO;
import com.example.service.entity.Comment;
import com.example.service.repository.CommentRepo;
import lombok.RequiredArgsConstructor;
import com.example.service.mapper.CommentMapper;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CommentService {

    private final CommentRepo commentRepo;
    private final CommentMapper commentMapper = new CommentMapper();

    public CommentDTO getComDto (Integer id){
        Optional<CommentDTO> comment = commentRepo.findById(id).map(commentMapper::toCommentDTO);
        if (comment.isPresent()){
            return comment.get();
        }
        return null;
    }

    public Comment getComById(Integer id) {
        Optional<Comment> optionalComment = commentRepo.findById(id);
        if (optionalComment.isPresent()) {
            return optionalComment.get();
        }
        return null;
    }

    public Comment saveComment(Comment comment) {
        return commentRepo.save(comment);
    }

}

