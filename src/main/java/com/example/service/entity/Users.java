package com.example.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NamedEntityGraph(
        name = "Users.allComments",
        attributeNodes = {
                @NamedAttributeNode(value = "comments", subgraph = "commentsUser")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "commentsUser",
                        attributeNodes = {
                                @NamedAttributeNode("userOne"),
                                @NamedAttributeNode("car")
                        }
                )
        }
)

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy="userOne", cascade = CascadeType.ALL,orphanRemoval = true)
    //@BatchSize(size=10)
    private List<Comment> comments;
}