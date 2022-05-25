package com.dh.PI.controllers;

import com.dh.PI.dto.Characteristics.CharacteristicsDTO;
import com.dh.PI.services.CharacteristicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characteristics")
public class CharacteristicsController {

    @Autowired
    private CharacteristicService service;

    @PostMapping
    public ResponseEntity<CharacteristicsDTO> create(@RequestBody CharacteristicsDTO characteristicsDTO){
        return ResponseEntity.status(201).body(service.create(characteristicsDTO));
    }
}
