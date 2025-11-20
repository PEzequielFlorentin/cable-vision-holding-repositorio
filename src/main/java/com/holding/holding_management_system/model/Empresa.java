package com.holding.holding_management_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotNull(message = "La fecha de entrada es obligatoria")
    @PastOrPresent(message = "La fecha de entrada debe ser en el pasado o el presente")
    private LocalDate fechaEntrada;

    @NotNull(message = "La facturación anual es obligatoria")
    @Positive(message = "La facturación anual debe ser un número positivo")
    private Double facturacionAnual;

    @NotNull(message = "El número de vendedores es obligatorio")
    @PositiveOrZero(message = "El número de vendedores debe ser mayor o igual a 0")
    private Integer numeroVendedores;

    @ManyToOne(optional = false) // Relación con Pais, no permite nulos
    @JoinColumn(name = "pais_id", nullable = false)
    private Pais sede;

    //Relacion entre Empresa y area
    @ManyToMany
    @JoinTable(
        name = "empresa_area",
        joinColumns = @JoinColumn(name = "empresa_id"),
        inverseJoinColumns = @JoinColumn(name = "area_id")
    )
    private List<Area> areas = new ArrayList<>();

    //Relacion entre empresa y vendedor
    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<Vendedor> vendedores = new ArrayList<>();

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

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Double getFacturacionAnual() {
        return facturacionAnual;
    }

    public void setFacturacionAnual(Double facturacionAnual) {
        this.facturacionAnual = facturacionAnual;
    }

    public Integer getNumeroVendedores() {
        return numeroVendedores;
    }

    public void setNumeroVendedores(Integer numeroVendedores) {
        this.numeroVendedores = numeroVendedores;
    }

    public Pais getSede() {
        return sede;
    }
    public List<Vendedor> getVendedores() {
        return vendedores;
    }
    
    public void setVendedores(List<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    public void setSede(Pais sede) {
        this.sede = sede;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }
}