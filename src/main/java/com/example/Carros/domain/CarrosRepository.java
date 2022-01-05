package com.example.Carros.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarrosRepository extends JpaRepository<Carro, Long> {

    List<Carro> findByTipo(String tipo);
}
