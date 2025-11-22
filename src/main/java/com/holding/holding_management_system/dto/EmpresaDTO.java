package com.holding.holding_management_system.dto;

import java.util.List;
import java.time.LocalDate;

public class EmpresaDTO {
    private Long id;
    private String nombre;
    private Double facturacionAnual;
    private Integer numeroVendedores;
    private Long paisId; // Solo el nombre del país
    private List<String> areas; // Lista de nombres de áreas
    private List<String> vendedores; // Lista de nombres de vendedores
    private LocalDate fechaEntrada;
    private Long sedePaisId;

    // Constructor sin argumentos (necesario para Jackson)
    public EmpresaDTO() {
    }

    // Constructor con 5 argumentos
    public EmpresaDTO(Long id, String nombre, Double facturacionAnual, Integer numeroVendedores, Long paisId) {
        this.id = id;
        this.nombre = nombre;
        this.facturacionAnual = facturacionAnual;
        this.numeroVendedores = numeroVendedores;
        this.paisId = paisId;
    }

    // Constructor completo con todos los campos
    public EmpresaDTO(Long id, String nombre, Double facturacionAnual, Integer numeroVendedores, Long paisId, List<String> areas, List<String> vendedores, LocalDate fechaEntrada) {
        this.id = id;
        this.nombre = nombre;
        this.facturacionAnual = facturacionAnual;
        this.numeroVendedores = numeroVendedores;
        this.paisId = paisId;
        this.areas = areas;
        this.vendedores = vendedores;
        this.fechaEntrada = fechaEntrada;
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
    
    public Long getSedePaisId() {
        return sedePaisId;
    }

    public void setSedePaisId(Long sedePaisId) {
        this.sedePaisId = sedePaisId;
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

    public Long getPais() {
        return paisId;
    }

    public void setPais(Long paisId) {
        this.paisId = paisId;
    }

    public List<String> getAreas() {
        return areas;
    }

    public void setAreas(List<String> areas) {
        this.areas = areas;
    }

    public List<String> getVendedores() {
        return vendedores;
    }

    public void setVendedores(List<String> vendedores) {
        this.vendedores = vendedores;
    }
    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }
}
