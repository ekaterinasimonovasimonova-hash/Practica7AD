package com.practica.practica7Transportes.service;

import com.practica.practica7Transportes.domain.Ruta;
import com.practica.practica7Transportes.domain.Transportista;
import com.practica.practica7Transportes.domain.User;
import com.practica.practica7Transportes.repository.RutaRepository;
import com.practica.practica7Transportes.security.UserContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RutaService {

    private final RutaRepository rutaRepository;
    private final UserContext userContext;

    public RutaService(RutaRepository rutaRepository, UserContext userContext) {
        this.rutaRepository = rutaRepository;
        this.userContext = userContext;
    }

    private boolean isAdmin(Set<User.Role> roles) {
        return roles.contains(User.Role.ADMIN);
    }

    @Transactional
    public Ruta crearRuta(Ruta ruta) {
        User currentUser = userContext.getCurrentUser();
        if (!isAdmin(currentUser.getRoles())) {
            throw new RuntimeException("Solo ADMIN puede crear rutas");
        }
        return rutaRepository.save(ruta);
    }

    public Ruta buscarPorId(Long id) {
        Ruta ruta = rutaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada"));

        User currentUser = userContext.getCurrentUser();
        Set<User.Role> roles = currentUser.getRoles();

        if (!isAdmin(roles)) {
            boolean asignada = ruta.getTransportista().stream()
                    .anyMatch(t -> t.getUser().getId().equals(currentUser.getId()));
            if (!asignada) {
                throw new RuntimeException("No existe o "
                        + "no tienes permisos suficientes para ver esta ruta");
            }
        }

        return ruta;
    }

    @Transactional
    public Ruta actualizarRuta(Long id, Ruta rutaActualizada) {
        Ruta ruta = buscarPorId(id);
        User currentUser = userContext.getCurrentUser();
        if (!isAdmin(currentUser.getRoles())) {
            throw new RuntimeException("Solo ADMIN puede actualizar rutas");
        }

        ruta.setLugarSalida(rutaActualizada.getLugarSalida());
        ruta.setLugarLlegada(rutaActualizada.getLugarLlegada());
        ruta.setHoraSalida(rutaActualizada.getHoraSalida());
        ruta.setHoraLlegada(rutaActualizada.getHoraLlegada());

        return rutaRepository.save(ruta);
    }

    @Transactional
    public void eliminarRuta(Long id) {
        Ruta ruta = buscarPorId(id);
        User currentUser = userContext.getCurrentUser();
        if (!isAdmin(currentUser.getRoles())) {
            throw new RuntimeException("Solo ADMIN puede eliminar rutas");
        }

        rutaRepository.delete(ruta);
    }

    public List<Ruta> listarTodas() {
        User currentUser = userContext.getCurrentUser();
        Set<User.Role> roles = currentUser.getRoles();

        if (isAdmin(roles)) {
            return rutaRepository.findAll();
        } else {
            // Solo las rutas asignadas al transportista
            Long userId = currentUser.getId();
            return rutaRepository.findByTransportistaUserId(userId);
        }
    }
}

