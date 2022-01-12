package com.example.Carros;

import com.example.Carros.domain.Carro;
import com.example.Carros.service.CarrosService;
import com.example.Carros.domain.DTO.CarroDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CarrosServiceTests {
	@Autowired
	CarrosService service;

	@Test
	public void save() {

		Carro carro = new Carro();
		carro.setNome("Grande carro");
		carro.setTipo("esportivos");
		CarroDTO c = service.insert(carro);

		assertNotNull(c);

		Long id = c.getId();
		assertNotNull(id);

		c = service.getCarroById(id);
		assertNotNull(c);
		;
		assertEquals("Grande carro", c.getNome());
		assertEquals("esportivos", c.getTipo());

		service.delete(id);



	}

	@Test
	public void testLista(){
		List<CarroDTO> carros = service.getCarros();
		assertEquals(30, carros.size());

	}

	@Test
	public void testGet(){
		CarroDTO c = service.getCarroById(11L);
		assertNotNull(c);


		assertEquals("Ferrari FF", c.getNome());

	}
	@Test
	public void testListaPorTipo(){

		assertEquals(10, service.getCarrosByTipo("classicos").size());
		assertEquals(10, service.getCarrosByTipo("esportivos").size());
		assertEquals(10, service.getCarrosByTipo("luxo").size());
		assertEquals(0, service.getCarrosByTipo("xxx").size());

	}
}
