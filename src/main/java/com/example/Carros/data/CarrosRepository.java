package com.example.Carros.data;

import com.example.Carros.domain.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarrosRepository extends JpaRepository<Carro, Long> {

    List<Carro> findByTipo(String tipo);
}
