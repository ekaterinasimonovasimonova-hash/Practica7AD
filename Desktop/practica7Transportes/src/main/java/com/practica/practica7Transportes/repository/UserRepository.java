package com.practica.practica7Transportes.repository;

import com.practica.practica7Transportes.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    
    Optional<User> findByEmail(String email);

    //Opcional
    Optional<User> findByUsername(String username);
}
