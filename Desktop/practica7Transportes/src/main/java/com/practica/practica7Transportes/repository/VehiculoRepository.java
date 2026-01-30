package com.practica.practica7Transportes.repository;

import com.practica.practica7Transportes.domain.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long>{
    
    Optional<Vehiculo> findByTransportistaId(Long transportistaId);

    public List<Vehiculo> findByTransportistaUserId(Long id);
    
}
