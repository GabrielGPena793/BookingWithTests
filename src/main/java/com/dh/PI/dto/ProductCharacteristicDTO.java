package com.dh.PI.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductCharacteristicDTO implements Serializable {

    private String name;
    private String description;
    private String icon;

}
