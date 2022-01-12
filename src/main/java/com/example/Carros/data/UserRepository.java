package com.example.Carros.data;

import com.example.Carros.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {

    User findByLogin(String Login);
}
