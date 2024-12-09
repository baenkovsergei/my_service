package com.example.service.service;

import com.example.service.entity.Cars;
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
    private final UsersRepo usersRepo;
    private final CarsRepo carsRepo;

    public Comment getComById(Integer id) {
        Optional<Comment> optionalComment = commentRepo.findById(id);
        if (optionalComment.isPresent()) {
            return optionalComment.get();
        }
        return null;
    }

    //Неэффективно с точки зрения запросов к базе данных
    public List<Comment> getComByUsrCar(String name,String model){
        Integer usrId = usersRepo.findUsersByUsername(name).getId();
        Integer carId = carsRepo.findByModel(model).getId();
        List<Comment> comments = commentRepo.findCommByUsrCar(usrId,carId);
        if (comments.isEmpty()) {
            return null;
        }
        return comments;
    }

    public List<Comment> getComByUsrCar2(String name,String model){
        List<Comment> comments = commentRepo.findCommByUsrCar(name,model);
        if (comments.isEmpty()) {
            return null;
        }
        return comments;
    }

    public Comment saveComment(Comment comment) {
        return commentRepo.save(comment);
    }

}
