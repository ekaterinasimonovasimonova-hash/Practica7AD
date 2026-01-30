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
    @PreAuthorize("hasRole('ADMIN')")
    public Transportista crear(@RequestBody Transportista transportista) {
        return transportistaService.crearTransportista(transportista);
    }

    //Consultar datos
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TRANSPORTISTA')")
    public Transportista buscarPorId(@PathVariable Long id) {
        return transportistaService.buscarPorId(id);
    }

    //Modificar transportista
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Transportista actualizar(
            @PathVariable Long id,
            @RequestBody Transportista transportista
    ) {
        return transportistaService.actualizarTransportista(id, transportista);
    }

    //Eliminar transportista
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void eliminar(@PathVariable Long id) {
        transportistaService.eliminarTransportista(id);
    }

    //Asignar vehículo
    @PutMapping("/{id}/vehiculo/{vehiculoId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void asignarVehiculo(
            @PathVariable Long id,
            @PathVariable Long vehiculoId
    ) {
        transportistaService.asignarVehiculo(id, vehiculoId);
    }

    //Asignar ruta
    @PutMapping("/{id}/rutas/{rutaId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void asignarRuta(
            @PathVariable Long id,
            @PathVariable Long rutaId
    ) {
        transportistaService.asignarRuta(id, rutaId);
    }
    
    //Desasignar ruta
    @DeleteMapping("/{id}/rutas/{rutaId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void desasignarRuta(
            @PathVariable Long id,
            @PathVariable Long rutaId
    ) {
        transportistaService.desasignarRuta(id, rutaId);
    }

    //Ver mis datos
    @GetMapping("/me")
    @PreAuthorize("hasRole('TRANSPORTISTA')")
    public Transportista verMisDatos() {
        
    return transportistaService.verMisDatos();
    }
}

