package com.example.service.controller;

import com.example.service.dto.CommentDTO;
import com.example.service.entity.Comment;
import com.example.service.mapper.CommentMapper;
import com.example.service.service.CommentService;
import com.example.service.service.CarsService;
import com.example.service.service.UsersService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/comments/")
@RequiredArgsConstructor

public class CommentController {

   public final CommentService commentService;
   private final UsersService usersService;
   private final CarsService carsService;
   @Autowired
   private final CommentMapper commentMapper;

   @GetMapping("/{id}")
   public ResponseEntity<CommentDTO> getComment(@PathVariable("id") Integer id) {
      return ResponseEntity.ok().body(commentMapper.toCommentDTO(commentService.getComById(id)));
   }

   /*Выдаёт три запроса в базу, поскольку в методе сервиса выполняется доступ к трём сущностям через
   три репозитория, для уменьшения количества запросов необходимо изменить сами сущности(???), поскольку для
   получения комментариев необходимо знать id пользователя и машины, а чтобы их получить по имени и модели
   необходимо выполнить запросы в таблицы пользователей и машин соответственно*/
   @GetMapping("/search")
   public ResponseEntity<List<CommentDTO>> getCommByUsrCar(@RequestParam(name = "name") String name,
                                                           @RequestParam(name = "model") String model) {
      List<Comment> comments = commentService.getComByUsrCar(name, model);
      return ResponseEntity.ok().body(commentMapper.toCommentDTO(comments));
   }


   @PostMapping("/")
   public ResponseEntity<Comment> createComment(@RequestParam(name = "id") Integer id,
                                                @RequestParam(name = "commentContent") String commentContent,
                                                @RequestParam(name = "user_id") Integer userId,
                                                @RequestParam(name = "car_id") Integer carId ) {
      Comment comment = new Comment(id,commentContent, usersService.getUserById(userId),carsService.getCarById(carId));
      return ResponseEntity.ok(commentService.saveComment(comment));
   }
}
