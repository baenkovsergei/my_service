package com.example.service.controller;

import com.example.service.dto.CommentDTO;
import com.example.service.entity.Users;
import com.example.service.mapper.CommentMapper;
import com.example.service.service.CommentService;
import com.example.service.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;
    @Autowired
    private final CommentMapper commentMapper;

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users users) {
        return ResponseEntity.ok(usersService.save(users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<CommentDTO>> getUserComments(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(commentMapper.toCommentDTO(usersService.getCommentById(id)));
    }


}
