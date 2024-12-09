package com.example.service.service;

import com.example.service.entity.Users;
import com.example.service.entity.Comment;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.service.repository.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UsersService {

    private final UsersRepo usersRepo;

    public Users save(Users users) {
        return usersRepo.save(users);
    }

    public Users getUserById(Integer id) {
        Optional<Users> optionalUser = usersRepo.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        return null;
    }

    public Users getUserByUsername(String username) {
        Users users = usersRepo.findUsersByUsername(username);
        if (users != null) {
            return users;
        }
        return null;
    }

    public Comment saveComment(Integer userId, Comment comment) {
        Optional<Users> optionalUser = usersRepo.findById(userId);
        if (optionalUser.isPresent()) {
            optionalUser.get().getComments().add(comment);
        }
        return null;
    }

    public List<Comment> getCommentById(Integer id) {
        Optional<Users> optionalUser = usersRepo.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get().getComments();
        }
        return null;
    }

    public List<String> getAllUsersName() {
        return usersRepo.findAll().stream().map(Users::getName).collect(Collectors.toList());
    }

    public List<Users> getAllUsers() {
        return usersRepo.findAllUsers();
    }

}
