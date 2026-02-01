package com.practica.practica7Transportes.controller;

import com.practica.practica7Transportes.domain.Transportista;
import com.practica.practica7Transportes.service.TransportistaService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transportistas")
@CrossOrigin(origins = "*")
public class TransportistaController {

    private final TransportistaService transportistaService;

    public TransportistaController(TransportistaService transportistaService) {
        this.transportistaService = transportistaService;
    }

    //Crear transportista
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Transportista crear(@RequestBody Transportista transportista) {
        return transportistaService.crearTransportista(transportista);
    }

    //Consultar datos
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Transportista buscarPorId(@PathVariable Long id) {
        return transportistaService.buscarPorId(id);
    }

    //Modificar transportista
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Transportista actualizar(
            @PathVariable Long id,
            @RequestBody Transportista transportista
    ) {
        return transportistaService.actualizarTransportista(id, transportista);
    }

    //Eliminar transportista
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void eliminar(@PathVariable Long id) {
        transportistaService.eliminarTransportista(id);
    }

    //Asignar vehículo
    @PutMapping("/{id}/vehiculo/{vehiculoId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void asignarVehiculo(
            @PathVariable Long id,
            @PathVariable Long vehiculoId
    ) {
        transportistaService.asignarVehiculo(id, vehiculoId);
    }
    //Desasisgnar vehículo
    @DeleteMapping("/{id}/vehiculo")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void desasignarVehiculo(@PathVariable Long id) {
        transportistaService.desasignarVehiculo(id);
    }

    //Asignar ruta
    @PutMapping("/{id}/rutas/{rutaId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void asignarRuta(
            @PathVariable Long id,
            @PathVariable Long rutaId
    ) {
        transportistaService.asignarRuta(id, rutaId);
    }
    
    //Desasignar ruta
    @DeleteMapping("/{id}/rutas/{rutaId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void desasignarRuta(
            @PathVariable Long id,
            @PathVariable Long rutaId
    ) {
        transportistaService.desasignarRuta(id, rutaId);
    }

    //Ver mis datos
    @GetMapping("/me")
    @PreAuthorize("hasAuthority('ROLE_TRANSPORTISTA')")
    public Transportista verMisDatos() {
    return transportistaService.verMisDatos();
    }
}

