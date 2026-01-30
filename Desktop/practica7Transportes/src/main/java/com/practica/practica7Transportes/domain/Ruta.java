package com.practica.practica7Transportes.domain;


import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Ruta")
@AllArgsConstructor
@NoArgsConstructor
public class Ruta {
    
    //Columnas
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "horas", nullable = false, length = 50)
    private double horas;
    
    @Column(name = "lugarSalida", nullable = false, length = 100)
    private String lugarSalida;
    
    @Column(name = "lugarLlegada", nullable = false, length = 100)
    private String lugarLlegada;
    
    @Column(name = "horaSalida", nullable = false, length = 100)
    private String horaSalida;
    
    @Column(name = "horaLlegada", nullable = false, length = 100)
    private String horaLlegada;
    
    //Relaciones
    @ManyToMany(mappedBy = "rutas")
    private List<Transportista> transportista;

    //Constructores
    public Ruta(double horas, String lugarSalida, String lugarLlegada, String horaSalida, String horaLlegada) {
        this.horas = horas;
        this.lugarSalida = lugarSalida;
        this.lugarLlegada = lugarLlegada;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
    }

    //Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getHoras() {
        return horas;
    }

    public void setHoras(double horas) {
        this.horas = horas;
    }

    public String getLugarSalida() {
        return lugarSalida;
    }

    public void setLugarSalida(String lugarSalida) {
        this.lugarSalida = lugarSalida;
    }

    public String getLugarLlegada() {
        return lugarLlegada;
    }

    public void setLugarLlegada(String lugarLlegada) {
        this.lugarLlegada = lugarLlegada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public List<Transportista> getTransportista() {
        return transportista;
    }

    public void setTransportista(List<Transportista> transportista) {
        this.transportista = transportista;
    }  
}

