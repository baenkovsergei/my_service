package com.example.service.mapper;

import com.example.service.dto.CarsDTO;
import com.example.service.entity.Cars;
import org.springframework.stereotype.Component;


@Component
public class CarsMapper {

    private final CommentMapper commentMapper = new CommentMapper();
    private final CategoryMapper categoryMapper = new CategoryMapper();

    public CarsDTO toCarsDTO(Cars cars) {
        return new CarsDTO(cars.getModel(),
                categoryMapper.listCatToDto(cars.getCategories()),
                commentMapper.toCommentDTO(cars.getComments()));
    }

}
