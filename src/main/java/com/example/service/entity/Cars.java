package com.example.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.util.List;
import java.util.Set;

@NamedEntityGraph(
        name = "Cars.allDetails",
        attributeNodes = {
                @NamedAttributeNode("categories"),
                @NamedAttributeNode(value = "comments", subgraph = "commentsUser")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "commentsUser",
                        attributeNodes = @NamedAttributeNode("userOne")

                )
        }
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cars")
public class Cars {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String model;

    @ManyToMany
    //@BatchSize(size = 10) //FetchType.Eager
    @JoinTable(
            name = "car_categories",
            joinColumns = @JoinColumn(name = "cars_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    Set<Category> categories;

    @OneToMany(mappedBy="car", cascade = CascadeType.ALL,orphanRemoval = true)
    @BatchSize(size = 10)
    private List<Comment> comments;
}
