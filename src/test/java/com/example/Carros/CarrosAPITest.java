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
import org.springframework.http.HttpStatus;
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

    private ResponseEntity<List<CarroDTO>> getCarros(String url) {
        return rest.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CarroDTO>>() {
        });


    }

    @Test
    public void testSave(){
        Carro carro = new Carro();
        carro.setNome("Porshe");
        carro.setTipo("esportivos");

        //Teste de inserção
        ResponseEntity response = rest.postForEntity("api/v1/carros", carro, null);
        System.out.println(response);

        //Verfica a criação
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        //Busca o objeto
        String location = response.getHeaders().get("location").get(0);
        CarroDTO c = getCarro(location).getBody();

        assertNotNull(c);
        assertEquals("Porshe", c.getNome());
        assertEquals("esportivos", c.getTipo());

        //Deleta
        rest.delete(location);

        //verifica se apagou
        assertEquals(HttpStatus.NOT_FOUND, getCarro(location).getStatusCode());

    }

    @Test
    public void testLista(){
        List<CarroDTO> carros = getCarros("/api/v1/carros").getBody();
        assertNotNull(carros);
        assertEquals(30, carros.size());
    }
    @Test
    public void testListaPorTipo(){
        assertEquals(10, getCarros("/api/v1/carros/classicos").getBody().size());
        assertEquals(10, getCarros("/api/v1/carros/esportivos").getBody().size());
        assertEquals(10, getCarros("/api/v1/carros/luxo").getBody().size());

        assertEquals(HttpStatus.NO_CONTENT, getCarros("/api/v1/carros/tipo/xxx").getStatusCode());
    }

    @Test
    public void testGetOk(){
        ResponseEntity<CarroDTO> response = getCarro("/api/v1/carros/11");
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        CarroDTO c = response.getBody();
        assertEquals("Ferrari FF", c.getNome());
    }

    @Test
    public void testGetNotFound(){
        ResponseEntity response = getCarro("/api/v1/carros/1100");
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

}
