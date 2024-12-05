package com.example.service.service;

import com.example.service.entity.Comment;
import com.example.service.repository.CommentRepo;
import com.example.service.repository.UsersRepo;
import com.example.service.repository.CarsRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CommentService {

    private final CommentRepo commentRepo;
    //testing
    private final UsersRepo usersRepo;
    private final CarsRepo carsRepo;

    public Comment getComById(Integer id) {
        Optional<Comment> optionalComment = commentRepo.findById(id);
        if (optionalComment.isPresent()) {
            return optionalComment.get();
        }
        return null;
    }

    public List<Comment> getComByUsrCarId(Integer userId, Integer carId) {
        List<Comment> comments = commentRepo.findCommByUsrCar(userId, carId);
        if (comments.size() > 0) {
            return comments;
        }
        return null;
    }

    //Не работает
    public List<Comment> getComByUsrCar(String name,String model){
        Integer usrId = usersRepo.findUsersByUsername(name).getId();
        Integer carId = carsRepo.findByModel(model).getId();
        List<Comment> comments = commentRepo.findCommByUsrCar(usrId,carId);
        if (comments.size() > 0) {
            return comments;
        }
        return null;
    }

    public Comment saveComment(Comment comment) {
        return commentRepo.save(comment);
    }

}
