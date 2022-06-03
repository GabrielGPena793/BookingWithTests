package com.dh.PI.repositories;

import com.dh.PI.model.Category;
import com.dh.PI.model.City;
import com.dh.PI.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {

    @EntityGraph(attributePaths = {"category", "city", "productCharacteristics"})
    Page<Product> findAll(Pageable pageable);
    @EntityGraph(attributePaths = {"images", "category", "city", "productCharacteristics"})
    Optional<Product> findById(Long id);
    @EntityGraph(attributePaths = {"images", "category", "city", "productCharacteristics"})
    List<Product> findAllByCategory(Category category);
    @EntityGraph(attributePaths = {"images", "category", "city", "productCharacteristics"})
    Page<Product> findAllByCategoryQualification(String qualification, Pageable pageable);
    @EntityGraph(attributePaths = {"images", "category", "city", "productCharacteristics"})
    Page<Product> findAllByCityName(String city, Pageable pageable);

    @Query("SELECT p FROM Product p JOIN FETCH p.bookings b JOIN FETCH p.city c WHERE c.name = (:cityName) " +
            "AND b.startDate NOT BETWEEN (:start) AND (:end)" +
            "AND b.endDate NOT BETWEEN (:start) AND (:end)")
    List<Product> findByCityNameBetweenStartDateAndEndDate(@Param("cityName") String cityName,
                                                           @Param("start") LocalDate start,
                                                           @Param("end") LocalDate end);

}
