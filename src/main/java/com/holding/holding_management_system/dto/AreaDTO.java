package com.holding.holding_management_system.dto;

import java.util.List;

public class AreaDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private List<Long> empresas; // IDs de las empresas asociadas

    // Constructor vacío
    public AreaDTO() {}

    // Constructor completo
    public AreaDTO(Long id, String nombre, String descripcion, List<Long> empresas) {
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

    public List<Long> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Long> empresas) {
        this.empresas = empresas;
    }
}