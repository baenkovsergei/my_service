package com.example.service.entity;

import jakarta.persistence.*;
import lombok.*;
import com.example.service.entity.Users;
import java.util.List;
import com.example.service.entity.Cars;

@NoArgsConstructor
@Data
@Entity
@Table(name = "comment")

public class Comment {
    @Id
    private Integer id;

    @Column(nullable = false)
    private String commentContent;

    @Setter
    @ManyToOne
    @JoinColumn(name="users_id") //nullable = false
    private Users userOne;

    @Setter
    @ManyToOne
    @JoinColumn(name="cars_id")
    private Cars car;

    public Comment(Integer id, String commentContent, Users user, Cars car) {
        this.id = id;
        this.userOne = user;
        this.car = car;
        this.commentContent = commentContent;
    }

}
