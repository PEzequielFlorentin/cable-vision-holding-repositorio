package com.holding.holding_management_system.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    @ManyToMany(mappedBy = "areas") // Relación bidireccional con Empresa
    private List<Empresa> empresas;

    // Constructor vacío (requerido por JPA)
    public Area() {}

    // Constructor completo
    public Area(Long id, String nombre, String descripcion, List<Empresa> empresas) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.empresas = empresas;
    }

    // Getters y Setters
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }
}
