package com.dh.PI.controllers;

import com.dh.PI.dto.bookingDTO.BookingRequestDTO;
import com.dh.PI.services.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class BookingControllerTest {

    @InjectMocks
    private BookingController bookingController;
    @Mock
    private BookingService service;

    private BookingRequestDTO bookingRequestDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startSetup();
    }

    @Test
    void create() {
    }

    @Test
    void findById() {
    }

    @Test
    void findAllByProduct() {
    }

    @Test
    void findAllByUser() {
    }

    private void startSetup() {

    }
}