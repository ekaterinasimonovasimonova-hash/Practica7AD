package com.practica.practica7Transportes.repository;

import com.practica.practica7Transportes.domain.Transportista;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportistaRepository extends JpaRepository<Transportista, Long>{
    
    Optional<Transportista> findByUserId(Long userId);
    
}
