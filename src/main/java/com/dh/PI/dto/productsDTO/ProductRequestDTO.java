package com.dh.PI.dto.productsDTO;

import com.dh.PI.model.Characteristic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductRequestDTO implements Serializable {

    private Long id;
    private String name;
    private String description;
    private String category;
    private String city;
    private Set<String> characteristics;

}