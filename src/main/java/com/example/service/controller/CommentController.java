package com.example.service.controller;

import com.example.service.dto.CommentDTO;
import com.example.service.entity.Comment;
import com.example.service.mapper.CommentMapper;
import com.example.service.service.CarsService;
import com.example.service.service.CommentService;
import com.example.service.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

   @GetMapping("/search")
   public ResponseEntity<List<CommentDTO>> getCommByUsrCar2(@RequestParam(name = "name") String name,
                                                           @RequestParam(name = "model") String model) {
      List<Comment> comments = commentService.getComByUsrCar2(name, model);
      return ResponseEntity.ok().body(commentMapper.toCommentDTO(comments));
   }

   // Old adding
//   @PostMapping("/")
//   public ResponseEntity<Comment> createComment(@RequestParam(name = "id") Integer id,
//                                                @RequestParam(name = "commentContent") String commentContent,
//                                                @RequestParam(name = "user_id") Integer userId,
//                                                @RequestParam(name = "car_id") Integer carId ) {
//      Comment comment = new Comment(id,commentContent, usersService.getUserById(userId),carsService.getCarById(carId));
//      return ResponseEntity.ok(commentService.saveComment(comment));
//   }

   @PostMapping("/add")
   public ResponseEntity<?> addComment(@RequestBody CommentDTO commentDTO){
      try {
         commentService.addComment(commentDTO);
         Map<String, Object> response = new HashMap<>();
         response.put("success", 201);
         response.put("message", "Comment added successfully");
         return new ResponseEntity<>(response, HttpStatus.CREATED);
      } catch (IllegalArgumentException e) {
         return ResponseEntity.badRequest().body(e.getMessage());
      }
   }

   @PostMapping("/populate")
   public ResponseEntity<String> populateComment(@RequestParam(name = "count") Integer count) {
      commentService.populateComments(count);
      return ResponseEntity.ok().body("Comment populated:" + count);
   }

}
