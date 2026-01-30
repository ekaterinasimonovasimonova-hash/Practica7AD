package com.practica.practica7Transportes.service;

import com.practica.practica7Transportes.domain.Transportista;
import com.practica.practica7Transportes.domain.User;
import com.practica.practica7Transportes.repository.TransportistaRepository;
import com.practica.practica7Transportes.security.UserContext;
import jakarta.transaction.Transactional;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class TransportistaService {
    
    private final TransportistaRepository transportistaRepository;
    private final UserContext userContext;

    public TransportistaService(TransportistaRepository transportistaRepository, UserContext userContext) {
        this.transportistaRepository = transportistaRepository;
        this.userContext = userContext;
    }

    private boolean isAdmin(Set<User.Role> roles) {
        return roles.contains(User.Role.ADMIN);
    }

    @Transactional
    public Transportista crearTransportista(Transportista transportista) {
        User currentUser = userContext.getCurrentUser();
        if (!isAdmin(currentUser.getRoles())) {
            throw new RuntimeException("Solo ADMIN puede crear transportistas");
        }
        return transportistaRepository.save(transportista);
    }

    public Transportista buscarPorId(Long id) {
        User currentUser = userContext.getCurrentUser();
        Set<User.Role> roles = currentUser.getRoles();

        Transportista transportista = transportistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transportista no encontrado"));

        if (!isAdmin(roles) && !transportista.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("No existe o no tienes permisos suficientes");
        }
        return transportista;
    }

    @Transactional
    public Transportista actualizarTransportista(Long id, Transportista transportistaActualizado) {
        Transportista transportista = buscarPorId(id);
        User currentUser = userContext.getCurrentUser();
        if (!isAdmin(currentUser.getRoles())) {
            throw new RuntimeException("Solo ADMIN puede actualizar transportistas");
        }
        transportista.setNombre(transportistaActualizado.getNombre());

        return transportistaRepository.save(transportista);
    }

    @Transactional
    public void eliminarTransportista(Long id) {
        Transportista transportista = buscarPorId(id);
        User currentUser = userContext.getCurrentUser();
        if (!isAdmin(currentUser.getRoles())) {
            throw new RuntimeException("Solo ADMIN puede eliminar transportistas");
        }
        transportistaRepository.delete(transportista);
    }
    
    @Transactional
    public Transportista verMisDatos() {
    User currentUser = userContext.getCurrentUser();
    return transportistaRepository.findByUserId(currentUser.getId())
            .orElseThrow(() -> new RuntimeException("No tienes transportista asignado"));
    }
}
