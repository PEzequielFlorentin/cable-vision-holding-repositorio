package com.holding.holding_management_system.dto;

import java.util.List;

public class AsesorDTO {
    private Long id; // ID del asesor
    private String codigo; // Código único del asesor
    private String nombre; // Nombre del asesor
    private String direccion; // Dirección del asesor
    private String titulacion; // Titulación del asesor
    private List<Long> areas; // IDs de las áreas asociadas
    private List<Long> empresas; // IDs de las empresas asociadas

    // Constructor vacío (necesario para frameworks como Jackson)
    public AsesorDTO() {
    }

    // Constructor completo
    public AsesorDTO(Long id, String codigo, String nombre, String direccion, String titulacion, List<Long> areas, List<Long> empresas) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.titulacion = titulacion;
        this.areas = areas;
        this.empresas = empresas;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTitulacion() {
        return titulacion;
    }

    public void setTitulacion(String titulacion) {
        this.titulacion = titulacion;
    }

    public List<Long> getAreas() {
        return areas;
    }

    public void setAreas(List<Long> areas) {
        this.areas = areas;
    }

    public List<Long> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Long> empresas) {
        this.empresas = empresas;
    }
}
