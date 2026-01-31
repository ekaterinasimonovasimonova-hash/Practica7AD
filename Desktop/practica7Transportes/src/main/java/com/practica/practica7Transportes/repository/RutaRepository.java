package com.practica.practica7Transportes.repository;

import com.practica.practica7Transportes.domain.Ruta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RutaRepository extends JpaRepository<Ruta, Long>{
    
    List<Ruta> findByTransportistaId(Long transportistaId);

    List<Ruta> findByTransportistaUserId(Long userId);
}
