package com.example.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Проверка
    private Integer id;

    @Column(nullable = false)
    private String commentContent;

    @ManyToOne
    @JoinColumn(name="users_id",nullable = false)
    private Users userOne;

    @ManyToOne
    @JoinColumn(name="cars_id",nullable = false)
    private Cars car;
}

