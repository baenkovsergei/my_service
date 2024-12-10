package com.example.service.entity;

import jakarta.persistence.*;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    private Integer id;

    @Column(nullable = false)
    private String commentContent;

    @ManyToOne
    @JoinColumn(name="users_id") //nullable = false
    private Users userOne;

    @ManyToOne
    @JoinColumn(name="cars_id")
    private Cars car;
}

