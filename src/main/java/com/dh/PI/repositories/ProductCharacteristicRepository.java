package com.dh.PI.repositories;

import com.dh.PI.model.ProductCharacteristic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCharacteristicRepository extends JpaRepository<ProductCharacteristic, Long> {
    List<ProductCharacteristic> findAllById(Long id);
}
