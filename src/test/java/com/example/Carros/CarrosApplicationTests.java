package com.example.Carros;

import com.example.Carros.domain.Carro;
import com.example.Carros.domain.CarrosService;
import com.example.Carros.domain.DTO.CarroDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CarrosApplicationTests {
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

		Optional<CarroDTO> op = Optional.ofNullable(service.getCarroById(id));
		assertTrue(op.isPresent());

		c = op.get();
		assertEquals("Grande carro", c.getNome());
		assertEquals("esportivos", c.getTipo());

		service.delete(id);

	}

	@Test
	public void testLista(){
		List<CarroDTO> carros = service.getCarros();
		assertEquals(30, carros.size());

	}

}
