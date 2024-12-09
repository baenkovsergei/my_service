package com.example.service.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.util.List;

@NamedEntityGraph(
        name = "car-with-category-and-comm",
        attributeNodes = {
           @NamedAttributeNode("model"),
           @NamedAttributeNode("categories"),
           @NamedAttributeNode("comments"),
        }
)

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "cars")
public class Cars {
    @Id
    private Integer id;
    private String model;

    @ManyToMany
    //@BatchSize(size = 10) //FetchType.Eager
    @JoinTable(
            name = "car_categories",
            joinColumns = @JoinColumn(name = "cars_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    List<Category> categories;


    @OneToMany(mappedBy="car")
    @BatchSize(size = 10)
    private List<Comment> comments;
}
