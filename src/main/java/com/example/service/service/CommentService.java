package com.example.service.service;

import com.example.service.utils.RandomString;
import com.example.service.entity.Comment;
import com.example.service.repository.CommentRepo;
import com.example.service.repository.UsersRepo;
import com.example.service.repository.CarsRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

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

    //Заполнение данными для тестирования
    public void populateComments(Integer count){
        int start = commentRepo.findAll().size();
        Random random = new Random();
        int carsSize = carsRepo.findAll().size();
        int usersSize = usersRepo.findAll().size();
        for (Integer i = start+1; i < (count+start+1);i++) {
            Comment comment = new Comment();
            comment.setId(i);
            comment.setCommentContent(RandomString.getRandomComment(15,8));
            comment.setCar(carsRepo.findById(random.nextInt(carsSize)).get());
            comment.setUserOne(usersRepo.findById(random.nextInt(usersSize)).get());
            commentRepo.save(comment);
        }
    }
}
