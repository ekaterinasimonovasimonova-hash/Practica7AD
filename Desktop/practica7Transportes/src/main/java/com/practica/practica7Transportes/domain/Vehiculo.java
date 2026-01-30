package com.practica.practica7Transportes.domain;

import jakarta.persistence.*;

@Entity
@Table(name="Vehiculo")
public class Vehiculo {
    
    //Columnas
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="Color", nullable= false, length = 50)
    private String color;
    
    @Column(name="Modelo", nullable= false, length = 50)
    private String modelo;
    
    @Column(name="Peso", nullable= false, length = 50)
    private double peso;
    
    //Relaciones
    @OneToOne
    @JoinColumn(name="Transportista_Id")
    private Transportista transportista;

    //Constructores
    public Vehiculo() {
    }

    public Vehiculo(String color, String modelo, double peso, Transportista transportista) {
        this.color = color;
        this.modelo = modelo;
        this.peso = peso;
        this.transportista = transportista;
    }

    //Setters y getters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Transportista getTransportista() {
        return transportista;
    }

    public void setTransportista(Transportista transportista) {
        this.transportista = transportista;
    }
}

