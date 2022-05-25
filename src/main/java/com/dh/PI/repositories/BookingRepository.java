package com.dh.PI.repositories;

import com.dh.PI.model.Booking;
import com.dh.PI.model.Product;
import com.dh.PI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findAllByProduct(Product product);

    List<Booking> findAllByUser(User user);
}
