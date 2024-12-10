package com.example.service.controller;

import com.example.service.dto.CommentDTO;
import com.example.service.entity.Comment;
import com.example.service.dto.UsersDTO;
import com.example.service.entity.Users;
import com.example.service.mapper.CommentMapper;
import com.example.service.mapper.UsersMapper;
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
    private final UsersMapper usersMapper;

    @GetMapping("/")
    public ResponseEntity<List<UsersDTO>> getUsers() {
        return ResponseEntity.ok(usersMapper.usersListToDto(usersService.getAllUsers()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<CommentDTO>> getUserCommentsById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(commentMapper.toCommentDTO(usersService.getCommentById(id)));
    }

    @GetMapping("/allnames")
    public ResponseEntity<List<String>> getUserNames() {
        return ResponseEntity.ok(usersService.getAllUsersName());
    }

    @GetMapping("/comments/{name}")
    public ResponseEntity<UsersDTO> getUserCommentsByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(usersMapper.toDto(usersService.getUserByUsername(name)));
    }

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users users) {
        return ResponseEntity.ok(usersService.save(users));
    }
    @PostMapping("/{id}/comment")
    public ResponseEntity<Comment> addComment(@PathVariable Integer id,
                                              @RequestBody Comment comment) {
        return ResponseEntity.ok(usersService.saveComment(id, comment));
    }

    //Заполнение для тестирования
    @PostMapping("/populate")
    public ResponseEntity<String> populateUsers(@RequestParam Integer count,
                                                @RequestParam String name) {
        usersService.populateUsers(count, name);
        return ResponseEntity.ok("Users populated:" + count);
    }

}
