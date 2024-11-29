package com.example.service.service;
import com.example.service.entity.Comment;
import com.example.service.entity.Users;
import com.example.service.entity.Cars;
import com.example.service.repository.CommentRepo;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class CommentService {

    private final CommentRepo commentRepo;

    public Comment saveComment(Comment comment) {
        return commentRepo.save(comment);
    }

}

