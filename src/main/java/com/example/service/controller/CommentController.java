package com.example.service.controller;

import com.example.service.dto.CommentDTO;
import com.example.service.entity.Comment;
import com.example.service.mapper.CommentMapper;
import com.example.service.service.CommentService;
import com.example.service.service.CarsService;
import com.example.service.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/comments/")
@RestController
@RequiredArgsConstructor

public class CommentController {

   public final CommentService commentService;
   private final UsersService usersService;
   private final CarsService carsService;

   @GetMapping("/{id}")
   public ResponseEntity<CommentDTO> getComment(@PathVariable("id") Integer id) {
      return ResponseEntity.ok().body(commentService.getComDto(id));
   }


   @PostMapping("/")
   //так не получится
   public ResponseEntity<Comment> createComment(@RequestParam(name = "id") Integer id,
                                                @RequestParam(name = "commentContent") String commentContent,
                                                @RequestParam(name = "user_id") Integer userId,
                                                @RequestParam(name = "car_id") Integer carId ) {
      Comment comment = new Comment(id,commentContent, usersService.getUserById(userId),carsService.getCarById(carId));

      return ResponseEntity.ok(commentService.saveComment(comment));

   }

}
