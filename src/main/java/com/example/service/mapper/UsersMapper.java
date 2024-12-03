package com.example.service.mapper;

import com.example.service.dto.UsersDTO;
import com.example.service.entity.Users;
import com.example.service.mapper.CommentMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsersMapper {

    CommentMapper commentMapper = new CommentMapper();

    public UsersDTO toDto(Users user) {
        return new UsersDTO(user.getName(),commentMapper.toCommentDTO(user.getComments()));
    }

    public List<UsersDTO> usersListToDto(List<Users> users){
        List<UsersDTO> usersDTO = new ArrayList<>();
        for (Users user : users) {
            usersDTO.add(toDto(user));
        }
        return usersDTO;
    }

}
