package com.example.service.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private List<Comment> comments;

}