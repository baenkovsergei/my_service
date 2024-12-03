package com.example.service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

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
