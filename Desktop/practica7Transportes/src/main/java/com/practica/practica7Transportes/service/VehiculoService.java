package com.practica.practica7Transportes.service;

import com.practica.practica7Transportes.domain.Vehiculo;
import com.practica.practica7Transportes.domain.User;
import com.practica.practica7Transportes.repository.TransportistaRepository;
import com.practica.practica7Transportes.repository.VehiculoRepository;
import com.practica.practica7Transportes.security.UserContext;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;
    private final TransportistaRepository transportistaRepository;
    private final UserContext userContext;

    public VehiculoService(VehiculoRepository vehiculoRepository,
            TransportistaRepository transportistaRepository, UserContext userContext) {
        this.vehiculoRepository = vehiculoRepository;
        this.transportistaRepository = transportistaRepository;
        this.userContext = userContext;
    }

    private boolean isAdmin(Set<User.Role> roles) {
        return roles.contains(User.Role.ADMIN);
    }

    @Transactional
    public Vehiculo crearVehiculo(Vehiculo vehiculo) {
        User currentUser = userContext.getCurrentUser();
        if (!isAdmin(currentUser.getRoles())) {
            throw new RuntimeException("Solo ADMIN puede crear vehículos");
        }
        return vehiculoRepository.save(vehiculo);
    }

    public Vehiculo buscarPorId(Long id) {
        Vehiculo vehiculo = vehiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

        User currentUser = userContext.getCurrentUser();
        if (!isAdmin(currentUser.getRoles()) &&
            !vehiculo.getTransportista().getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("No existe vehículo o "
                    + "no tienes permisos suficientes para ver este vehículo");
        }
        return vehiculo;
    }

    @Transactional
    public Vehiculo actualizarVehiculo(Long id, Vehiculo vehiculoActualizado) {
        Vehiculo vehiculo = buscarPorId(id);
        User currentUser = userContext.getCurrentUser();
        if (!isAdmin(currentUser.getRoles())) {
            throw new RuntimeException("Solo ADMIN puede actualizar vehículos");
        }

        vehiculo.setModelo(vehiculoActualizado.getModelo());
        vehiculo.setPeso(vehiculoActualizado.getPeso());

        return vehiculoRepository.save(vehiculo);
    }

    @Transactional
    public void eliminarVehiculo(Long id) {
        Vehiculo vehiculo = buscarPorId(id);
        User currentUser = userContext.getCurrentUser();
        if (!isAdmin(currentUser.getRoles())) {
            throw new RuntimeException("Solo ADMIN puede eliminar vehículos");
        }
        vehiculoRepository.delete(vehiculo);
    }
    @Transactional
    public List<Vehiculo> listarVehiculos() {
        User currentUser = userContext.getCurrentUser();
        if (!isAdmin(currentUser.getRoles())) {
            throw new RuntimeException("Solo ADMIN puede listar vehículos");
        }
        return vehiculoRepository.findAll();
    }

    @Transactional
    public Vehiculo verMiVehiculo() {
        User currentUser = userContext.getCurrentUser();
        return vehiculoRepository.findByTransportistaUserId(currentUser.getId())
                .orElseThrow(() -> new RuntimeException("No tienes un vehículo asignado"));
    }
}
