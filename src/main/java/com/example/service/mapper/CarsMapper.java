package com.example.service.mapper;

import com.example.service.dto.CarsDTO;
import com.example.service.entity.Cars;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class CarsMapper {

    private final CommentMapper commentMapper = new CommentMapper();
    private final CategoryMapper categoryMapper = new CategoryMapper();

    public CarsDTO toCarsDTO(Cars cars) {
        return new CarsDTO(cars.getModel(),
                categoryMapper.listCatToDto(cars.getCategories()),
                commentMapper.toCommentDTO(cars.getComments()));
    }

    public List<CarsDTO> toCarsDTO(List<Cars> cars) {
        return cars.stream().map(this::toCarsDTO).collect(Collectors.toList());
    }

}
