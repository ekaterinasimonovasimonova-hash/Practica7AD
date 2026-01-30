package com.practica.practica7Transportes.domain;

import jakarta.persistence.*;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Transportista")
@AllArgsConstructor
@NoArgsConstructor
public class Transportista {
    
    //Columnas
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="nombre", nullable=false, length = 100)
    private String nombre;
    
    @Column(name="apellido1", nullable=false, length = 100)
    private String apellido1;
   
    //Relaciones
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    
    @OneToOne(mappedBy = "transportista")
    private Vehiculo vehiculo;
    
    @ManyToMany
    @JoinTable(
            name = "Transportista_Ruta",
            joinColumns = @JoinColumn(name = "transportista_id"),
            inverseJoinColumns = @JoinColumn(name = "ruta_id")
    )
    private List<Ruta> rutas = new ArrayList<>();

    // Constructores
    public Transportista(String nombre, String apellido1) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
    }

    //Setters y getters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public List<Ruta> getRutas() {
        return rutas;
    }

    public void setRutas(List<Ruta> rutas) {
        this.rutas = rutas;
    }    
}
