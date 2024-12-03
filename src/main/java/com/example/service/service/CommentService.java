package com.example.service.service;

import com.example.service.entity.Comment;
import com.example.service.repository.CommentRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CommentService {

    private final CommentRepo commentRepo;

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
