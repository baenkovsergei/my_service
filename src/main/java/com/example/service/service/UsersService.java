package com.example.service.service;

import com.example.service.dto.CommentDTO;
import com.example.service.entity.Cars;
import com.example.service.entity.Users;
import com.example.service.entity.Comment;
import java.util.List;
import java.util.Optional;

import com.example.service.mapper.CommentMapper;
import com.example.service.repository.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UsersService {

    private final UsersRepo usersRepo;
    private final CommentMapper commentMapper = new CommentMapper();

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

    public List<CommentDTO> getCommentsByUserId (Integer id) {
        Optional<Users> optionalUser = usersRepo.findById(id);
        if (optionalUser.isPresent()) {
            return commentMapper.toCommentDTO(optionalUser.get().getComments());
        }
        return null;
    }



}
