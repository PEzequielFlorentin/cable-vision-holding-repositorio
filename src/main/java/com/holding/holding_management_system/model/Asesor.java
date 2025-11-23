package com.holding.holding_management_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Asesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_asesor", nullable = false)
    private String codigo;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "La dirección no puede estar vacía")
    private String direccion;

    @NotBlank(message = "La titulación no puede estar vacía")
    private String titulacion;

    @ManyToMany
    @JoinTable(
        name = "asesor_area",
        joinColumns = @JoinColumn(name = "asesor_id"),
        inverseJoinColumns = @JoinColumn(name = "area_id")
    )
    private List<Area> areas;

    @ManyToMany
    @JoinTable(
        name = "asesor_empresa",
        joinColumns = @JoinColumn(name = "asesor_id"),
        inverseJoinColumns = @JoinColumn(name = "empresa_id")
    )
    private List<Empresa> empresas;

    // Constructor vacío
    public Asesor() {}

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

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }
}