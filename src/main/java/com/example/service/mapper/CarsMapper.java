package com.example.service.mapper;

import com.example.service.dto.CarsDTO;
import com.example.service.entity.Cars;
import org.springframework.stereotype.Component;
import com.example.service.mapper.CommentMapper;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarsMapper {

    private final CommentMapper commentMapper = new CommentMapper();

    public CarsDTO toCarsDTO(Cars cars) {
        return new CarsDTO(cars.getModel(),
                cars.getCategories(),
                commentMapper.toCommentDTO(cars.getComments()));
    }

}
