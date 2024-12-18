package com.example.service.mapper;

import com.example.service.dto.CarsDTO;
import com.example.service.entity.Cars;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
@AllArgsConstructor
public class CarsMapper {

    private final CommentMapper commentMapper;

    public CarsDTO toCarsDTO(Cars cars) {
        return new CarsDTO(cars.getModel(),
                cars.getCategories(),
                commentMapper.toCommentDTOList(cars.getComments())); //Previous commentMapper.toCommentDTO(cars.getComments()));
    }

    public List<CarsDTO> toCarsDTO(List<Cars> cars) {
        return cars.stream().map(this::toCarsDTO).collect(Collectors.toList());
    }

}
