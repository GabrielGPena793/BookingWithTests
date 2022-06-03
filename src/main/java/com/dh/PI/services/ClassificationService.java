package com.dh.PI.services;

import com.dh.PI.dto.ScoreDTO;
import com.dh.PI.dto.productsDTO.ProductResponseDTO;
import com.dh.PI.exceptions.LoginException;
import com.dh.PI.model.Classification;
import com.dh.PI.model.Product;
import com.dh.PI.model.User;
import com.dh.PI.repositories.ClassificationRepository;
import com.dh.PI.repositories.ProductRepository;
import com.dh.PI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClassificationService {

    @Autowired
    private ClassificationRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public ProductResponseDTO saveScore(ScoreDTO dto) {

        User user = userRepository.findByEmail(dto.getUserEmail());
        if(user == null) {
            throw new LoginException("Precisa logar para dar pontuação");
        }

        Product product = productRepository.findById(dto.getProductId()).get();

        Classification classificationUser = repository.findByUserIdAndProductId(user.getId(), product.getId());

        if (classificationUser == null){
            Classification classification = new Classification();
            classification.setProductId(dto.getProductId());
            classification.setUserId(user.getId());
            classification.setScore(dto.getScore());

            repository.saveAndFlush(classification);
        }else {
            classificationUser.setProductId(dto.getProductId());
            classificationUser.setUserId(user.getId());
            classificationUser.setScore(dto.getScore());
            repository.saveAndFlush(classificationUser);
        }


        double sum = 0.0;
        for (Classification c : product.getClassifications()) {
            sum += c.getScore();
        }

        double avg = sum / product.getClassifications().size();

        product.setScore(avg);
        product.setCount(product.getClassifications().size());


        return new ProductResponseDTO(productRepository.save(product));
    }
}
