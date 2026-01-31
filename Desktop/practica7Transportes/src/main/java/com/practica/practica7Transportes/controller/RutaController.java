package com.practica.practica7Transportes.controller;

import com.practica.practica7Transportes.domain.Ruta;
import com.practica.practica7Transportes.service.RutaService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rutas")
@CrossOrigin(origins = "*")
public class RutaController {

    private final RutaService rutaService;

    public RutaController(RutaService rutaService) {
        this.rutaService = rutaService;
    }

    //Crear ruta
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Ruta crear(@RequestBody Ruta ruta) {
        return rutaService.crearRuta(ruta);
    }

    //Modificar ruta
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Ruta actualizar(@PathVariable Long id, @RequestBody Ruta ruta) {
        return rutaService.actualizarRuta(id, ruta);
    }

    //Eliminar ruta
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void eliminar(@PathVariable Long id) {
        rutaService.eliminarRuta(id);
    }

    //Listar para ADMIN
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Ruta> listar() {
        return rutaService.listarRutas();
    }
    
    
    //Listar para TRANSPORTISTA
    @GetMapping("/mis-rutas")
    @PreAuthorize("hasRole('TRANSPORTISTA')")
    public List<Ruta> listarMisRutas() {
        return rutaService.listarRutas();
    }

    //Consultar ruta por ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TRANSPORTISTA')")
    public Ruta buscarPorId(@PathVariable Long id) {
        return rutaService.buscarPorId(id);
    }
}

