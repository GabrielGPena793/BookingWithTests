package com.dh.PI.dto;

import com.dh.PI.dto.productsDTO.ProductResponseDTO;
import com.dh.PI.model.City;
import com.dh.PI.model.Product;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class CityDTO {

    private Long id;
    private String name;
    private String country;

    public CityDTO(City city) {
        this.id = city.getId();
        this.name = city.getName();
        this.country = city.getCountry();
    }
}
