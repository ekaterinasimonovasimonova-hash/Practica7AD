package com.practica.practica7Transportes.controller;

import com.practica.practica7Transportes.domain.Vehiculo;
import com.practica.practica7Transportes.service.VehiculoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
@CrossOrigin(origins = "*")
public class VehiculoController {

    private final VehiculoService vehiculoService;

    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    //Crear vehículo
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Vehiculo crear(@RequestBody Vehiculo vehiculo) {
        return vehiculoService.crearVehiculo(vehiculo);
    }

    //Consultar vehículo por id
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TRANSPORTISTA')")
    public Vehiculo buscarPorId(@PathVariable Long id) {
        return vehiculoService.buscarPorId(id);
    }

    //Modificar vehículo
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Vehiculo actualizar(@PathVariable Long id, @RequestBody Vehiculo vehiculo) {
        return vehiculoService.actualizarVehiculo(id, vehiculo);
    }

    //Eliminar vehículo
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void eliminar(@PathVariable Long id) {
        vehiculoService.eliminarVehiculo(id);
    }

    //Listar vehículos
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Vehiculo> listarTodos() {
        return vehiculoService.listarVehiculos();
    }

    //Consultar el vehículo asignado al transportista
    @GetMapping("/mio")
    @PreAuthorize("hasRole('TRANSPORTISTA')")
    public Vehiculo verMiVehiculo() {
        return vehiculoService.verMiVehiculo();
    }
}
