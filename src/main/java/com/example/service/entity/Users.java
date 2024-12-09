package com.example.service.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
public class Users {
    @Id
    private Integer id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy="userOne")
    @BatchSize(size=10)
    private List<Comment> comments;

}