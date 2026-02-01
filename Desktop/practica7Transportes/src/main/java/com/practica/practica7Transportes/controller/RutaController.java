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
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") //Añado el hasAuthority para recoger el rol del token
    public Ruta crear(@RequestBody Ruta ruta) {
        return rutaService.crearRuta(ruta);
    }

    //Modificar ruta
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Ruta actualizar(@PathVariable Long id, @RequestBody Ruta ruta) {
        return rutaService.actualizarRuta(id, ruta);
    }

    //Eliminar ruta
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void eliminar(@PathVariable Long id) {
        rutaService.eliminarRuta(id);
    }

    //Listar para ADMIN
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Ruta> listar() {
        return rutaService.listarRutas();
    }
    
    
    //Listar para TRANSPORTISTA
    @GetMapping("/mis-rutas")
    @PreAuthorize("hasAuthority('ROLE_TRANSPORTISTA')")
    public List<Ruta> listarMisRutas() {
        return rutaService.listarRutas();
    }

    //Consultar ruta por ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_TRANSPORTISTA')")
    public Ruta buscarPorId(@PathVariable Long id) {
        return rutaService.buscarPorId(id);
    }
}

