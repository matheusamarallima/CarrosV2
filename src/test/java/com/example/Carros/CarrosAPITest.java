package com.example.Carros;

import com.example.Carros.domain.Carro;
import com.example.Carros.domain.CarrosService;
import com.example.Carros.domain.DTO.CarroDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = CarrosApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarrosAPITest {
    @Autowired
    protected TestRestTemplate rest;

    private ResponseEntity<CarroDTO> getCarro(String url) {
        return rest.getForEntity(url, CarroDTO.class);
    }

    private ResponseEntity<List<CarroDTO>> getCarro2(String url) {
        return rest.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<CarroDTO>>() {
        });


    }

}
