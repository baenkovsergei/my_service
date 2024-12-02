package com.example.service.controller;

import com.example.service.dto.CommentDTO;
import com.example.service.entity.Users;
import com.example.service.service.UsersService;
import com.example.service.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/users/")
@RestController
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users users) {
        return ResponseEntity.ok(usersService.save(users));
    }

    @GetMapping
    public ResponseEntity<List<CommentDTO>> getUserComments(@RequestParam("userId") Integer userId) {
        return ResponseEntity.ok(usersService.getCommentsByUserId(userId));
    }
}
