package com.holding.holding_management_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;


@Entity
public class Asesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El código del asesor no puede estar vacío")
    private String codigoAsesor;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "La dirección no puede estar vacía")
    private String direccion;

    @NotBlank(message = "La titulación no puede estar vacía")
    private String titulacion;

    // Relación entre Asesor, Área y Empresa
    @ManyToMany
    @JoinTable(
        name = "asesor_area",
        joinColumns = @JoinColumn(name = "asesor_id"),
        inverseJoinColumns = @JoinColumn(name = "area_id")
    )
    private List<Area> areasCubiertas;

    // Relación entre Asesor, Área y Empresa
    @ManyToMany
    @JoinTable(
        name = "asesor_empresa",
        joinColumns = @JoinColumn(name = "asesor_id"),
        inverseJoinColumns = @JoinColumn(name = "empresa_id")
    )
    private List<Empresa> empresasAsesoradas;

    @PastOrPresent(message = "La fecha de inicio debe ser en el pasado o el presente")
    private LocalDate fechaInicio;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoAsesor() {
        return codigoAsesor;
    }

    public void setCodigoAsesor(String codigoAsesor) {
        this.codigoAsesor = codigoAsesor;
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

    public List<Area> getAreasCubiertas() {
        return areasCubiertas;
    }

    public void setAreasCubiertas(List<Area> areasCubiertas) {
        this.areasCubiertas = areasCubiertas;
    }

    public List<Empresa> getEmpresasAsesoradas() {
        return empresasAsesoradas;
    }

    public void setEmpresasAsesoradas(List<Empresa> empresasAsesoradas) {
        this.empresasAsesoradas = empresasAsesoradas;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
}
